#!/bin/bash

files_to_delete=".files_to_delete"

# password: test (sha1sum)
# arg - first line of template.tbl
cmp_passwords()
{
	password=$(sha1sum .tmp | head -c 40)

	if [[ $1 == $password ]]; then
		unlock_files
	else
		echo "Password was provided but not matched"
		exit 1
	fi
}

unlock_files()
{
	for ((i = 1 ; i < $n ; i++)); do
		chmod u=rw,og=rw ${lines[$i]}
	done

	while IFS= read -r line || [[ -n "$line" ]]; do
		rm -f $line
	done < $files_to_delete
}

# https://riptutorial.com/bash/example/19457/read-lines-of-a-file-into-an-array
lines=()

n=0

while IFS= read -r line || [[ -n "$line" ]]; do
	lines+=("$line")
	n=$((n+1))
done < template.tbl

if [ $# == 1 ]; then
	echo $1 > .tmp
    cmp_passwords "${lines[0]}"
    rm -f .tmp
    rm -f $files_to_delete
    exit 0
fi

for ((i = 1 ; i < $n ; i++)); do
	if ! [ -f ${lines[$i]} ]; then
		touch ${lines[$i]}
		echo ${lines[$i]} >> $files_to_delete
	fi

# https://www.howtogeek.com/437958/how-to-use-the-chmod-command-on-linux/
	chmod u=,og= ${lines[$i]}
done