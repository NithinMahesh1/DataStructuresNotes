
// Given a list of lists n integers combine the lists return one full sorted list at the end
// Build a system given patient data (millions of lines of data) and design it to be efficient enough. Data is just given to you (it is in another system)

public static List<Integer> secondElement() {
    List<List<Integer>> mainList = new ArrayList();
    List<Integer> combinedIntList = new ArrayList();

    for(int i=0; i<mainList.length(); i++) {
        List<Integer> intList = mainList.get(i);
        for(int j=0; j<intList.length(); j++) {
            int intResult = intList.get(j);
            combinedIntList.add(intResult);
        }
    }

    Lists.sort(combinedIntList);

}




// Reverse string problem
// Can be solved using one loop iterating in reverse

public static String reverseString(String str) {
    Stack<Characater> stack = new Stack<>();

    for(int i=0; i<str.length(); i++) {
        Char letters = str.charAt(i);
        stack.push(letters);
    }

    String concat = "";
    while(!stack.isEmpty()) {
        concat = concat + stack.pop();
    }

    return concat;
}




// I recently had a second round interview in which I was asked how to design an efficient system from scratch and I had no idea how to explain it to the interviewer. This is the question he asked me:

// Build a system given patient data (millions of lines of data) and design it to be efficient enough to handle this data set. Data is just given to you (it is in another system).

// Now I would say I am a sufficient programmer at the moment, but I have no had much experience (if any even) on designing a system from the ground up. During this interview I was so caught up on how I would go about getting the data from the first system into the application I would want to build. I kept asking the interviewer how the data looks and if I can get it from a database or somehow through REST calls, but he only told me that it would be from another system so I wasn't sure how to go about the rest of the question. Do you guys have any advice on how I can learn these things or even how I would answer this question?