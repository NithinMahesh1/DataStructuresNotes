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
        // Basically we want to change all the surounded O's into X's (ones that are not on borders)
        // We then use DFS on O's on the border and convert them to T's
        // This ensures we do not change the bordering ones and the connected ones to the border into X's
        HashSet<(int,int)> visited = new HashSet<(int, int)>();
        int rows = board.Length;
        int columns = board[0].Length;

        // Convert to border O -> T's
        for(int i=0; i<board.Length; i++) {
            for(int j=0; j<board[i].Length; j++) {
                if(board[i][j] == 'O' && (i == 0 || j == 0 
                        || i == rows-1 || j == columns-1)) {
                    // run dfs and convert them to T's
                    dfs(board,i,j,visited);
                }
            }
        }

        // Convert surounded O's -> X's
        for(int z=0; z<board.Length; z++) {
            for(int x=0; x<board[z].Length; x++) {
                if(board[z][x] == 'O' && (z != 0 || z != rows-1 
                        || x != 0 || x != columns-1)) {
                    board[z][x] = 'X';
                }
            }
        }

        // Convert T's -> O's
        for(int z=0; z<board.Length; z++) {
            for(int x=0; x<board[z].Length; x++) {
                if(board[z][x] == 'T') {
                    board[z][x] = 'O';
                }
            }
        }
        Console.WriteLine("done");
    }
    private void dfs(char[][] board, int r, int c, HashSet<(int,int)> visited) {
        if(r < 0 || r >= board.Length || c < 0 || c >= board[r].Length 
                || visited.Contains((r,c)) || board[r][c] == 'X') {
            return;
        }

        visited.Add((r,c));
        board[r][c] = 'T';
        dfs(board,r+1,c,visited);
        dfs(board,r-1,c,visited);
        dfs(board,r,c+1,visited);
        dfs(board,r,c-1,visited);
    }
}