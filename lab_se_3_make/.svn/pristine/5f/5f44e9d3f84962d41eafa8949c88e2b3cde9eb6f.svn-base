#!/bin/bash
# I'm so tired of make scripting so I wrote this script
# https://stackoverflow.com/questions/255898/how-to-iterate-over-arguments-in-a-bash-script
# https://stackoverflow.com/questions/12137431/test-if-a-command-outputs-an-empty-string

svn status > diff.temp

for class in "$@"
do
	grep "${class}" diff.temp > /dev/null

	if [[ $? == 0 ]]; then
    	svn add * --force
    	svn commit -m 'makefile commit'
	fi
done

rm -f diff.temp