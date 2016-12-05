#!/bin/bash

make clean;
mkdir $USER
cp -r responses.txt MergeSort ConjugateGradient PrefixScan Schedules tree_traversal $USER;
cd $USER/ConjugateGradient; rm -f *.rb; cd ../..;
cd $USER; find . -name "*.pdf" | xargs rm; cd ..;
tar zcvf $USER.tgz $USER;
rm -rf $USER;
mv $USER.tgz /mnt/n7fs/ens/tp_abuttari/rendus
chmod 600 /mnt/n7fs/ens/tp_abuttari/rendus/$USER.tgz 
