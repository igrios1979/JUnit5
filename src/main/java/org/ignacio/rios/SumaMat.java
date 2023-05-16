package org.ignacio.rios;

public class SumaMat {

    public static void main(String[] args) {
        int N =3;
        int [][] matriz1 ={{10,9,9},{4,5,6},{7,8,9}};
        int [][] matriz2 ={{1,4,5},{4,6,5},{5,9,3}};

        int[][] resultado = new int[N][N];

        for(int i = 0; i< N ; i++){

            for(int j=0;j < N;j++){

                resultado[i][j] = matriz1[i][j] + matriz2[i][j];


                                  }

                      }

        for(int i = 0; i< N ; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(resultado[i][j]+" ");
            }
            System.out.println();
        }
    }


}
