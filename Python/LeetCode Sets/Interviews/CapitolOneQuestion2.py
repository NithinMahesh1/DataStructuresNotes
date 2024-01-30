
# Example 1:
# logs= [
#     "switch branch1",
#     "push file1",
#     "push file2",
#     "switch branch2",
#     "push file1",
#     "push file2",
#     "push file3"
# ]

# Example 2:
# logs= [
#     "switch branch1",
#     "push file1",
#     "switch branch2",
#     "push file1",
#     "switch branch1",
#     "push file2",
# ]

# Find the branch with the most files and return the number of files


logs= [
    "switch branch1",
    "push file1",
    "switch branch2",
    "push file1",
    "switch branch1",
    "push file2",
]

branches = {}
maxcount = 0
CurrNumOfFiles = 0
for i in range(0,len(logs)):
    commands = logs[i]
    if("switch" in commands):
        splitBranches = commands.split()
        branchName = splitBranches[1]
        if(branchName in branches):
            CurrNumOfFiles = branches[branchName]
            branches.update({branchName:CurrNumOfFiles})
        else:
            branches[branchName] = 0
    elif("push" in commands):
        CurrNumOfFiles = branches[branchName]
        branches.update({branchName:CurrNumOfFiles + 1})
        

maxcount = max(branches, key=branches.get)

print(branches)
print(maxcount)