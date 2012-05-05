#!/usr/bin/python3

import random
import sys

verbs = []
nouns = []
adj = []
tenses = []

# read in config file
f = open(sys.argv[1], "r");
num = int(f.readline())
print(num)
for line in f:
	if line[0] == '#':
		pass
	elif line[-2] == 'o':
		nouns.append(line[0:-1])
	elif line[-2] == 'i':
		verbs.append(line[0:-2])
	elif line[-2] == 'a':
		adj.append(line[0:-1])
	else:
		tenses.append(line[0:-1])
f.close();

# function to generate sentences
def genSen(v, n, a, t):
	acc = ''
	

	return acc

'''	acc = ''
	acc += random.choice(['','la'])
	acc += ' '
	acc += random.choice(n)
	acc += ' '
	b = 1
	while (b == 1):
		b = random.choice([0,1])
		if (b == 1):
			acc += random.choice(a)
			acc += ' '
	acc += random.choice(v)
	acc += random.choice(t)
	acc += ' '
	acc += random.choice(n)
	pl = random.choice(['','j'])
	p = False
	if (pl == 'j'):
		p == True
	acc += pl
	acc += 'n '
	b = 1
	while (b == 1):
		b = random.choice([0,1])
		if (b == 1):
			acc += random.choice(a)
			if (p):
				acc += 'j'
			acc += 'n '
	acc += '.'
	return acc'''

# loop to generate sentences
count = 0
while (count < num):
	print(genSen(verbs, nouns, adj, tenses))
	count += 1
