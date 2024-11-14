# Hey!

If you're reading this, it's probable that you're a new CSC220 TA working for Dr. Wang. 

I won't lie, this codebase is kind of hectic, and a lot of it is legacy code (os was deprecated since python 2.7!), but it is not too bad once you get used to the structure of each lab and its correspoinding grading rubric and testing scripts. 

## Things you want to make sure you do: 

### Change hardcoded filepaths 

Make sure all the local paths that are hardcoded around `labXXscript.py` are modified to suit your local file structure

This is the main chunk of paths you want to have working:

```python3
students = [line.strip().split(',') for line in open(
    '/Users/<YourUsername>/TA/csc220-names.csv')]

distadd = "/Users/<YourUsername>/Library/CloudStorage/Box-Box/"
assignment = "Lab10"
assignmentfiles = ["MaxHeap.java", "HeapTester.java"]
disk_main_add = "/Users/<YourUsername>/TA/fall2024/lab10/"
```
I'm working on a Mac, so on a Linux or Windows machine the filepaths might look a bit different. 

You should also modify this hardcoded filepath in the `check_assignment_for_student(dist_disk)` function, which should be where the java lab packages are located: 

```python3
src_main = "/Users/<YourUsername>/TA/csc220-scripts_Automated_Grading_fromJerry/csc220-scripts/java/src/" + assignment.lower() + \
                "/" + main_file
```

I'll keep on adding to this later



