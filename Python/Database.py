import csv
import sys


class Database(object):
    def __init__(self):
        print("accessing database...")

    @staticmethod
    def read(file):  # reads csv database files
        keys = []
        with open('Database/'+file, 'r') as file:
            reader = csv.reader(file)
            try:
                for row in reader:
                    keys.append(row)
            except csv.Error as e:  # reports error and terminates program if one arises
                sys.exit('file %s, line %d: %s' % (file, reader.line_num, e))

        return keys

    @staticmethod
    def write(file, data):
        with open(file, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerows(data)


if __name__ == "__main__":
    database = Database()
    filename = input("File name: ")
    print(database.read(filename))
