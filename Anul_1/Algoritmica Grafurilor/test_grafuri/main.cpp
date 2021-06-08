#include <fstream>
#include <iostream>
#include <vector>
#include <climits>
#include <queue>
using namespace std;
ifstream f("file.in");
vector <int> lVecini[300], costuri[300];
queue <int> Q;
int d[300], parinte[300], rezultat[300];
int main()
{
    int n, m, x, y, c, Ei, Ef;
    f>>n>>m;
    for(int i=1; i<=m; i++){
        f>>x>>y>>c;
        lVecini[x].push_back(y);
        costuri[x].push_back(c);
    }
    cout<<"Introduceti nodul initial: ";
    cin>>Ei;
    cout<<"Introduceti nodul final: ";
    cin>>Ef;

    for(int i=0; i<=n; i++)
        if(i!=Ei) d[i]=LONG_MAX;

    Q.push(Ei);
    while(!Q.empty())
    {   x=Q.front();
        Q.pop();
        for(unsigned int i=0; i<lVecini[x].size(); i++)
            if(d[lVecini[x][i]] > d[x] + costuri[x][i])
            {   parinte[lVecini[x][i]]=x;
                d[lVecini[x][i]]=d[x]+costuri[x][i];
                Q.push(lVecini[x][i]);
            }
    }
    int k=d[Ef], k2=d[Ef];
    rezultat[k]=Ef;
    while(rezultat[k]!=Ei)
    {   rezultat[k-1]=parinte[rezultat[k]];
        k--;
    }

    cout<<'\n'<<"Drumul minim de cost "<<d[Ef]<<" este: ";
    for(int i=k; i<=k2; i++)
        cout<<rezultat[i]<<" ";
    return 0;
}
