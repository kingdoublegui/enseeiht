#!/bin/bash

make clean;
mkdir $USER
cp -r responses.txt derivative_free linked_list matrix_multiplication reduction synchronizations $USER;
cd $USER; find . -name "*.pdf" | xargs rm; cd ..;
tar zcvf $USER.tgz $USER;
rm -rf $USER;
mv $USER.tgz /mnt/n7fs/ens/tp_abuttari/rendus_2016
chmod 600 /mnt/n7fs/ens/tp_abuttari/rendus_2016/$USER.tgz 
