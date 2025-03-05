package org.hina.buoi7.Queen;

public class AllSolNQueen {
    private static int k = 0;

    private static void printSolution(boolean[][] board) {
        System.out.printf("%d-\n", ++k);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                System.out.printf(" %d ", board[i][j] ? 1 : 0);
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // kiếm tra hàng hàng ngang
        for (int i = 0; i < col; i++) {
            if (board[row][i])
                return false;
        }

        // kiểm tra đường chéo chính
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j])
                return false;

        // kiếm tra đường chéo phụ
        for (int i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j])
                return false;

        return true;
    }

    private static boolean solve(boolean[][] board, int col) {
        // điều kiện dừng
        if (col == board.length) {
            printSolution(board);
            return true;
        }

        // khi thêm một con hậu sẽ thêm vào trên cột mới và kiếm tra xem có bị con nào
        // ăn theo đường chéo hoặc đường ngang không

        // chạy các ô dọc trên cột mới để kiếm tra
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = true;
                res = solve(board, col + 1);
                board[i][col] = false;
            }
        }

        return res;
    }

    private static void solve(int n) {
        boolean[][] board = new boolean[n][n];

        solve(board, 0);
        if (k == 0)
            System.out.printf("Solution does not exist");
    }

    public static void main(String[] args) {
        solve(8);
    }
}
