import pandas as pd
import os
# Load the CSV file

#find the csv file in the current directory
df = pd.read_csv('/Users/CristobalLillo_1/TA/fall2024/lab08/Lab08/lab08_rubric.csv')

df.columns = df.columns.str.strip()

totals = df['Total']
weighted_totals = (totals / 100) * 4.5

write_file = open('finalgrades.txt', 'w')

for index, row in df.iterrows():
    name = row['Last Name'] + " " + row['First Name']
    c_number = row['Lab ID']
    weighted_total = weighted_totals[index]
    write_file.write(f"{name}, {c_number}, {weighted_total}\n")

write_file.close()