import pandas as pd
import os
# Set assignment number
assignment = '06'

# File paths
rubricPath = f"/Users/CristobalLillo_1/TA/fall2024/lab{assignment}/Lab{assignment}/lab{assignment}_rubric.csv"
rosterPath = '/Users/CristobalLillo_1/TA/csc220-names.csv'
gradingPath = f'/Users/CristobalLillo_1/TA/fall2024/finalGrades/lab{assignment}/'

if not os.path.exists(gradingPath): 
    os.makedirs(gradingPath)

# Read and process graded assignments
df = pd.read_csv(rubricPath)
df.columns = df.columns.str.strip()
df['weighted'] = (df['Total'] / 100) * 4.5
df = df.sort_values('First Name')

# Write grades to file
with open(gradingPath + f"lab{assignment}Grades.txt", 'w') as write_file:
    for index, row in df.iterrows():
        name = f"{row['First Name']} {row['Last Name']}"
        c_number = row['Lab ID']
        weighted_total = row['weighted']
        write_file.write(f"{name}, {c_number}, {weighted_total}\n")

# Get class roster and find missing submissions
with open(rosterPath) as roster_file:
    class_roster = [line.strip().split(',') for line in roster_file]
    student_ids = [student[0] for student in class_roster]

# Get submitted student IDs from graded assignments
graded_ids = df['Lab ID'].tolist()

# Find missing submissions
missing_ids = set(student_ids) - set(graded_ids)

# Print statistics
print(f"\nSubmission Statistics for Lab {assignment}:")
print(f"Total students in class: {len(student_ids)}")
print(f"Submissions received: {len(graded_ids)}")
print(f"Missing submissions: {len(missing_ids)}")
print(f"Submission rate: {(len(graded_ids)/len(student_ids)*100):.1f}%")



# Print missing submissions
if missing_ids:
    print("\nStudents who haven't submitted:")
    missing_students = [student for student in class_roster if student[0] in missing_ids]
    with open(gradingPath +"missing.txt", 'w') as write_file:
        for student in missing_students:
            print(f"ID: {student[0]}, Name: {student[1]}")
            write_file.write(f"ID: {student[0]}, Name: {student[1]}")
        