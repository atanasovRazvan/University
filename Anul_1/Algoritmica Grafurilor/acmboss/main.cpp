#include <iostream>

using namespace std;

int pie(int a, int b){

    int r=a%b;
    if(a<0) a=-a;
    if(b<0) b=-b;
    while(r)
    {   a=b;
        b=r;
        r=a%b;
    }
    cout<<b;
    return b;

}

struct card{
        int x, y;
        int tox, toy;
        int p;
    };

void readcard(card &C){

    cin>>C.x>>C.y>>C.tox>>C.toy>>C.p;

}

int main()
{
    int n, cmmdc, pret=0; cin>>n;
    card c;

    readcard(c);
    cmmdc=pie(c.tox, c.toy);
    pret+=c.p;
    if(cmmdc==1) {cout<<pret; return 0;}

    for(int i=1; i<n; i++){

        readcard(c);
        pret+=c.p;
        if(pie(cmmdc, pie(c.tox, c.toy))){

            cout<<pret<<'\n';
            return 0;

        }

    }

    cout<<-1;

    return 0;
}
