// You are given a 2-D matrix board containing 'X' and 'O' characters.

// If a continous, four-directionally connected group of 'O's is surrounded by 'X's, 
// it is considered to be surrounded.

// Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.

// Example 1:
// Input: board = [
//   ["X","X","X","X"],
//   ["X","O","O","X"],
//   ["X","O","O","X"],
//   ["X","X","X","O"]
// ]

// Output: [
//   ["X","X","X","X"],
//   ["X","X","X","X"],
//   ["X","X","X","X"],
//   ["X","X","X","O"]
// ]
// Explanation: Note that regions that are on the border are not considered surrounded regions.

using System.Reflection.Metadata;

public class Solution {
    public static void Main(string[] args) {
        
        char[][] board = new char[][]{
            new char[] {'X','X','X','X'},
            new char[] {'X','O','O','X'},
            new char[] {'X','O','O','X'},
            new char[] {'X','X','X','O'},
        };

        Solution obj = new Solution();
        obj.Solve(board);
    }
    public void Solve(char[][] board) {
        // BFS solution where we find the first occurence of 0 by iterating r/c in board
        // When we get to a 0 then we check (r+1,c), (r,c+1), and (r+1,c+1)
        // Once we confirm this we replace those values in the board with X's
        // return the modified board if those are the case and if not return board as is
        HashSet<(int,int)> visited = new HashSet<(int, int)>();

        for(int r=0; r<board.Length; r++) {
            for(int c=0; c<board[r].Length; c++) {
                if(board[r][c].CompareTo('O') == 0) {
                    // Confirm the 3 other pos
                    // Use DFS to convert them into "X's"
                    if(board[r+1][c].CompareTo('O') == 0 && board[r][c+1].CompareTo('O') == 0
                            && board[r+1][c+1].CompareTo('O') == 0) {
                        dfs(board,r,c,visited);
                    }
                }
            }
        }
        Console.WriteLine("done");
    }
    private void dfs(char[][] board, int r, int c, HashSet<(int,int)> visited) {
        if(board[r][c].CompareTo('O') != 0 || r < 0 || r >= board.Length || c < 0 
                || c >= board[r].Length || visited.Contains((r,c))) {
            return;
        }

        visited.Add((r,c));
        // Change the value to X
        board[r][c] = 'X';
    }
}