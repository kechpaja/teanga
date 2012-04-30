#!/usr/bin/python3

import sys

conjunctions = ['kaj', 'aŭ']
pronouns = ['mi', 'ci', 'li', 'ĝi', 'ŝi', 'ri', 'ni', 'vi', 'ili', \
		'tio', 'kio', 'io', 'nenio', 'ĉio', \
		'tia', 'kia', 'io', 'nenio', 'ĉio', \
		'tiu', 'kiu', 'io', 'nenio', 'ĉio', \
		'tie', 'kie', 'io', 'nenio', 'ĉio', \
		'tiam', 'kiam', 'io', 'nenio', 'ĉio', \
		'tiom', 'kiom', 'iom', 'neniom', 'ĉiom', \
		'tial', 'kial', 'ial', 'nenial', 'ĉial', \
		'tiel', 'kiel', 'iel', 'neniel', 'ĉiel', \
		'ties', 'kies', 'ies', 'nenies', 'ĉies', \

def pos(word):
	if word == 'la':
		return 'article'
	elif word in conjunctions:
		return 'conjunction'
	elif word[-1] == 'o':
		return 'noun'
	elif word[-1] == 'a':
		return 'adjective'
	elif word[-1] == 'i':
		return 'verb'
	elif word[-1] == 'e':
		return 'adverb'
	elif word in pronouns:
		return 'pronoun'
	else:
		return 'preposition'

f = open(sys.argv[1], 'r')
dest = open(sys.argv[2], 'w')

for line in f:
	spl = string.split(line)
	eng = spl[1]
	eo = spl[0]
	dest.write(eo)
	dest.write(eng)
	dest.write(pos(eo))
	dest.write("[None Given]")
	dest.write("")

f.close()
dest.close()
