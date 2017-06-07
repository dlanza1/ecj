#!/bin/bash

# Reference: http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
done
SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

HOME=$SCRIPT_DIR

problem_name="parity"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/parity/parity-groups.params -p jobs=30 -p breed.groups=1 -p generations=50 -p stat.file=${problem_name}.1.stat
for groups in 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/parity/parity-groups.params \
			-p jobs=30 \
			-p generations=75 \
			-p breed.groups=${groups} \
			-p stat.file=${problem_name}.${groups}.stat
done

problem_name="ant"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/ant/ant-groups.params -p jobs=30 -p breed.groups=1 -p generations=50 -p stat.file=${problem_name}.1.stat
for groups in 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/ant/ant-groups.params \
			-p jobs=30 \
			-p generations=75 \
			-p breed.groups=${groups} \
			-p stat.file=${problem_name}.${groups}.stat
done

problem_name="lawnmover"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/lawnmower/noadf-groups.params -p jobs=30 -p breed.groups=1 -p generations=50 -p stat.file=${problem_name}.1.stat
for groups in 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/lawnmower/noadf-groups.params \
                    	-p jobs=30 \
                	-p generations=75 \
                    	-p breed.groups=${groups} \
                    	-p stat.file=${problem_name}.${groups}.stat
done

problem_name="multiplexer"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/multiplexer/11-groups.params -p jobs=30 -p breed.groups=1 -p generations=50 -p stat.file=${problem_name}.1.stat
for groups in 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/multiplexer/11-groups.params \
                    	-p jobs=30 \
                	-p generations=75 \
                    	-p breed.groups=${groups} \
                    	-p stat.file=${problem_name}.${groups}.stat
done
 
problem_name="regression"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/regression/sexticerc-groups.params -p jobs=30 -p breed.groups=1 -p generations=50 -p stat.file=${problem_name}.1.stat
for groups in 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/regression/sexticerc-groups.params \
                    	-p jobs=30 \
                	-p generations=75 \
                    	-p breed.groups=${groups} \
                    	-p stat.file=${problem_name}.${groups}.stat
done

problem_name="royaltree"
java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/royaltree/royaltree-groups.params -p jobs=30 -p breed.groups=1 -p generations=500 -p stat.file=${problem_name}.1.stat
for groups in 2048 1024 512 256 128 64 32 16 8 4 2
do
    java -cp $HOME/target/ecj-23-SNAPSHOT.jar ec.Evolve -file $HOME/src/main/java/ec/app/royaltree/royaltree-groups.params \
                    	-p jobs=30 \
                	-p generations=750 \
                    	-p breed.groups=${groups} \
                    	-p stat.file=${problem_name}.${groups}.stat
done

