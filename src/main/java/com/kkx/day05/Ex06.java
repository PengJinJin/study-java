package com.kkx.day05;

import java.util.Scanner;

public class Ex06 {
	static char[][] board = new char[15][15];
	static boolean isBlack = true;

	/**
	 * 找上面的，看是不是有五个相同的
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	static boolean findUp(int x, int y) {
		int count = 0;// 当前棋子数
		int reCount = 0;// 反向棋子数
		for (int i = x; i > 0; i--) {
			if (board[i][y] == (isBlack ? '@' : 'o')) {
				if (++count >= 5) {
					return true;
				}
			} else if (board[i][y] == (isBlack ? 'o' : '@')) {
				reCount = i;
				break;
			} else {
				return false;
			}
		}
		return reCount - x >= 5;
	}

	public static void init() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '*';
			}
		}
	}

	// {
// {
// {a, a},
// {a,a}
// }
	public static char int2Char(int x) {
		if (x >= 0 && x <= 9) return (char) (x + '0');
		return (char) ('a' + x - 10);
	}

	public static void print() {
		System.out.print("  ");
		for (int i = 0; i < board.length; i++) {
			System.out.print(int2Char(i) + " ");
		}
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.print(int2Char(i) + " ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static int char2Int(char x) {
		if (x >= '0' && x <= '9') {
			return x - '0';
		}
		return x - 'a' + 10;
	}

	public static void main(String[] args) {
		init();
		print();
		Scanner sca = new Scanner(System.in);
		while (true) {
			System.out.println("请" + (isBlack ? "黑" : "白") + "方落子");
			String x = sca.nextLine();
			if (x.length() != 2) {
				System.out.println("落子坐标不正确，请重新落子");
				continue;

			}
			int row = char2Int(x.charAt(0));
			int col = char2Int(x.charAt(1));
			if (row < 0 || row > board.length || col < 0 || col > board[row].length) {
				System.out.println("位置不对");
				continue;
			}
			if (board[row][col] != '*') {
				System.out.println("已有落子，请重新选择位置");
				continue;
			}
			board[row][col] = isBlack ? '@' : 'o';
			print();
			//if (findUp(row, col)) {
			//	System.out.println((isBlack ? "黑" : "白") + "方胜利");
			//	break;
			//}
			if (isWinV(row, col) || isWinH(row, col) || isWinR(row, col)||isWinL(row,col)) {
				System.out.println((isBlack ? "黑" : "白") + "方胜利");
				break;
			}
			isBlack = !isBlack;
		}

	}

	/*
	垂直方向判断输赢
	 */
	public static boolean isWinV(int row, int col) {
		char color = board[row][col];
		int i = row;
		int count = 0;
		while (i > 0 && board[i][col] == color) {
			i--;
		}
		i++;
		while (i < board.length && board[i][col] == color) {
			i++;
			count++;
		}
		return count >= 5;
	}
	/*
	水平方向判断输赢
	*/

	public static boolean isWinH(int row, int col) {
		char color = board[row][col];
		int j = col;
		int count = 0;
		while (j > 0 && board[row][j] == board[row][col]) {
			j--;
		}
		j++;
		while (j < board[row].length && board[row][j] == board[row][col]) {
			j++;
			count++;
		}
		return count >= 5;

	}

	/*
	右斜方向判断输赢
	 */
	public static boolean isWinR(int row, int col) {
		char color = board[row][col];
		int x = row;
		int y = col;
		int count = 0;
		while (x > 0 && y < board[x].length && board[x][y] == color) {
			x--;
			y++;
		}
		x++;
		y--;
		while (x < board.length && y > 0 && board[x][y] == color) {
			x++;
			y--;
			count++;
		}
		return count >= 5;
	}

	/*
	左斜方向判断输赢
	 */
	public static boolean isWinL(int row,int col){
		char color=board[row][col];
		int x=row;
		int y=col;
		int count=0;
		while(x<board.length&&y<board[x].length&&board[x][y]==color){
			x++;
			y++;
		}
		x--;
		y--;
		while (x>0&&y>0&&board[x][y]==color){
			x--;
			y--;
			count ++;
		}
		return count >=5;
	}

}

