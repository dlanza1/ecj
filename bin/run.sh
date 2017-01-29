#!/bin/bash

# Reference: http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
done
SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

HOME=$SCRIPT_DIR/..

list_max_size=1000
generations=75

mkdir $HOME/out/ 2>/dev/null

problem_name="parity"
for evalthreads in 1 2 4 6 8
do
    java -cp $HOME/target/ecj-*.jar ec.Evolve \
    		-file $HOME/src/main/java/ec/app/parity/bloatcontrol/parity.params \
			-p jobs=30 \
			-p generations=${generations} \
			-p to-be-evaluated-individuals.list.maximum-size=${list_max_size} \
			-p evalthreads=${evalthreads} \
			-p stat.file=$HOME/out/${problem_name}.${evalthreads}.stat
done
