from random import Random

class test_generator:
    def __init__(self, n: int):
        self.n = n
        self.data = []

    def generator(self):
        rnd = Random()
        number1 = 0
        number2 = 0
        for i in range(self.n):
            number1 = rnd.randint(0, 32767)
            number2 = rnd.randint(0, 32767)
            self.data.append([number1, number2])

    def write_to_file(self):
        f = open(str(self.n)+".txt", "+w")
        f.write(str(self.n) + "\n")
        for line in self.data:
            f.write(str(line[0]) + " " + str(line[1]) + "\n")
        f.close()

def create_test(n):
    test = test_generator(n)
    test.generator()
    test.write_to_file()


if __name__ == '__main__':
    create_test(150)
    create_test(200)
    create_test(300)
    create_test(400)
    create_test(800)
    create_test(1600)
    create_test(3200)
    create_test(6400)
    create_test(12800)