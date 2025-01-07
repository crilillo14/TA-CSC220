### CSC 220 as taught by Dr. Zheng Wang, Victor Milenkovic does not use this system

LABS 4, 6, 8, 10, 12 fully functional and correspond to the CSC 220 syllabus as of Fall 2024.

If you still don't have Box Drive, click this link{https://www.box.com/resources/downloads}. Or if using another cloud service, get the corresponding tool that makes cloud files accesible locally. 

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


Workflow for this repo: 

...


