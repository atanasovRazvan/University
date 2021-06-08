
class GDRegression:

    def __init__(self):
        self.intercept_ = 0.0
        self.coef_ = []

    def fit(self, x, y, learningRate=0.01, noEpochs=1000):

        self.coef_ = [0.0 for _ in range(1 + len(x[0]))]  # beta or w coefficients y = w0 + w1 * x1 + w2 * x2 + ...

        for epoch in range(noEpochs):
            for i in range(len(x)):  # for each sample from the training data

                ycomputed = self.eval(x[i], self.coef_)  # estimate the output
                crtError = ycomputed - y[i]  # compute the error for the current sample
                for j in range(0, len(x[0])):  # update the coefficients

                    self.coef_[j + 1] = self.coef_[j + 1] - learningRate * crtError * x[i][j]
                self.coef_[0] = self.coef_[0] - learningRate * crtError * 1

        self.intercept_ = self.coef_[0]
        self.coef_ = self.coef_[1:]

    def eval(self, xi, coef):
        yi = coef[0]
        for j in range(len(xi)):
            yi += coef[j + 1] * xi[j]
        return yi

    def predict(self, inTest):
        coefficients = [self.intercept_] + [c for c in self.coef_]
        computedLabels = [self.eval(sample, coefficients) for sample in inTest]
        return computedLabels