
class Regressor:
    def __init__(self):
        self.__coef = []

    def fit(self, input_data, output_data):
        phase1 = self.__multiply(self.__transpusa(input_data), input_data)
        phase2 = self.__inversa(phase1)
        phase3 = self.__multiply(phase2, self.__transpusa(input_data))
        phase4 = self.__multiply(phase3, output_data)
        self.__coef = phase4
        return phase4

    def __minor(self, matrix, j_to_find):
        minor = []
        for i in range(1, len(matrix)):
            line = []
            for j in range(len(matrix)):
                if j != j_to_find:
                    line.append(matrix[i][j])
            minor.append(line)
        return minor

    def __determinant(self, matrix):
        if len(matrix) == 1:
            return matrix[0][0]
        if len(matrix) == 2:
            return matrix[0][0] * matrix[1][1] - matrix[1][0]*matrix[0][1]
        result = 0
        for j in range(len(matrix)):
            minor = self.__minor(matrix, j)
            result += ((-1) ** (j + 2)) * matrix[0][j] * self.__determinant(minor)

        return result

    def __transpusa(self, matrix):
        transp = []

        for i in range(len(matrix[0])):
            line = []
            for j in range(len(matrix)):
                line.append(matrix[j][i])
            transp.append(line)

        return transp

    def __minor2(self, matrix, i_find, j_find):
        minor = []
        for i in range(len(matrix)):
            if i == i_find:
                continue
            line = []
            for j in range(len(matrix[0])):
                if j != j_find:
                    line.append(matrix[i][j])
            if line:
                minor.append(line)
        return minor

    def __adj(self, matrix):
        adjMat = []
        for i in range(len(matrix)):
            line = []
            for j in range(len(matrix[0])):
                minor = (-1) ** (i+j) * self.__determinant(self.__minor2(matrix, i, j))
                line.append(minor)
            adjMat.append(line)
        return self.__transpusa(adjMat)

    def __inversa(self, matrix):
        determinant = self.__determinant(matrix)
        adj = self.__adj(matrix)
        return [[adj[i][j] / determinant for j in range(len(adj[0]))] for i in range(len(matrix))]

    def __multiply(self, matrix1, matrix2):
        matrix = [[0 for _ in range(len(matrix2[0]))] for _ in range(len(matrix1))]
        for i in range(len(matrix1)):
            for j in range(len(matrix2[0])):
                for k in range(len(matrix1[0])):
                    matrix[i][j] += matrix1[i][k] * matrix2[k][j]
        return matrix

    def predict(self, data):
        return sum([data[i] * self.__coef[i+1][0] for i in range(len(data))]) + self.__coef[0][0]