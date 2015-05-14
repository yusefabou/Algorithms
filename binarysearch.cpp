#include <iostream> 

int main() {
    int n = 5;
    int A[5] = {1,5,8,12,13};
    int k = 5;
    int B[5] = {8,1,23,1,11};

    int l = 0;
    int r = 0;
    int m = 0;
    for( int i = 0; i < k; i++) {
        l = 1;
        r = n;
        m = 0;
        while( l < r ) {
            m = (l+r)/2;
            if (A[m] == B[i]){
              std::cout << m;
              break;
            }
            else if (B[i] < A[m])
              r = m-1;
            else if (A[m] < B[i])
              l = m+1;
            else std::cout << "-1";
        }
    }
    return 0;
}
