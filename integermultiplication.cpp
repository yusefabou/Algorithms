#include <iostream>

int main() {
	int n;
	long x0;
	long y0;

	std::cout << 239030239030566179 << std::endl;
	std::cout << 56617956617923930 << std::endl;
    x0 = 239030239030566179;
	y0 = 56617956617923930;

	int x[] = {2,3,9,0,3,0,2,3,9,0,3,0,5,6,6,1,7,9};
	int y[] = {5,6,6,1,7,9,5,6,6,1,7,9,2,3,9,3,0};

	n = std::max(x.size(), y.size());
	std::cout << n << std::endl;



	return 0;
}

int left(int[] arr) {
	int digits = n/2;
	int total = 0;
	for(int i = 0; i < digits; i++) {
		total += arr[i]*10(n-i-1)
	}
}

int right(int[] arr){
	int digits = n/2
	int total = 0;
	for(int i = 0; i < digits; i++){
		total += arr[n-i-1]*10(i)
	}
}