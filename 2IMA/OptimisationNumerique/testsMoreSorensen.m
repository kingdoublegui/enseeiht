clear all;
close all;

%% Tests de More Sorensen
disp('### Tests de moreSorensen.m ###');

delta = 0.5;

%% Sur la fonction q1
disp('# Test sur la quadratique 1 #');
g1 = [0; 0];
H1 = [7 0; 0 2];

[ s ] = moreSorensen(g1, H1, delta);
disp(s);
etalon = etalonms(g1, H1, delta, 10^-8)

%% Sur la fonction q2
disp('# Test sur la quadratique 2 #');
g2 = [6; 2];
H2 = [7 0; 0 2];

[ s ] = moreSorensen(g2, H2, delta);
disp(s);
etalon =  etalonms(g2, H2, delta, 10^-8)

%% Sur la fonction q3
disp('# Test sur la quadratique 3 #');
g3 = [-2; 1];
H3 = [-2 0; 0 10];

[ s ] = moreSorensen(g3, H3, delta);
disp(s);
etalon = etalonms(g3, H3, delta, 10^-8)

%% Sur la fonction q4
disp('# Test sur la quadratique 4 #');
g4 = [0; 0];
H4 = [-2 0; 0 10];

[ s ] = moreSorensen(g4, H4, delta);
disp(s);
etalon = etalonms(g4, H4, delta, 10^-8)

%% Sur la fonction q5
disp('# Test sur la quadratique 5 #');
g5 = [2; 3];
H5 = [4 6; 6 5];

[ s ] = moreSorensen(g5, H5, delta);
disp(s);
etalon = etalonms(g5, H5, delta, 10^-8)

%% Sur la fonction q6
disp('# Test sur la quadratique 6 #');
g6 = [2; 0];
H6 = [4 0; 0 -15];

[ s ] = moreSorensen(g6, H6, delta);
disp(s);
etalon = etalonms(g6, H6, delta, 10^-8)
