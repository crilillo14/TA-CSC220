import pandas as pd

# Load the CSV file

assignment = 10
#find the csv file in the current directory
df = pd.read_csv(f'/Users/CristobalLillo_1/TA/fall2024/lab{assignment}/Lab{assignment}/lab{assignment}_rubric.csv')

df.columns = df.columns.str.strip()


df['weighted'] = (df['Total'] / 100) * 4.5
df.sort_values('First Name')

print(df)

write_file = open('/Users/CristobalLillo_1/TA/fall2024/lab10/Lab10/finalgrades.txt', 'w')

for index, row in df.iterrows():
    name = row['First Name'] + " " + row['Last Name']
    c_number = row['Lab ID']
    weighted_total = row['weighted']
    write_file.write(f"{name}, {c_number}, {weighted_total}\n")

write_file.close()