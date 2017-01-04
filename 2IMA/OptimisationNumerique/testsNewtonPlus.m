clear all;
close all;

%% Tests de newton pour les fonctions non lineaire (aka newtonplus)
disp('### Tests de newtonPlus.m ###');

% Sur la fonction phi1
disp('# Test sur phi1 #');
syms l;
delta = 0.5;
disp(phi1(l, @(l)ns2_1(l), delta));
% 
% l = -13;
% val = phi1(l, @(l)ns2_1(l), delta);
% disp(val);
[ t , lambda ] = newtonPlus(phi1(l, @(l)ns2_1(l), delta), 0, 50);
disp(lambda);


syms l;
delta = 0.2;
disp(phi1(l, @(l)ns2_2(l), delta));

l = -13;
val = phi1(l, @(l)ns2_2(l), delta);
disp(val);

syms l;
delta = 0.7;
disp(phi1(l, @(l)ns2_2(l), delta));

l = -13;
val = phi1(l, @(l)ns2_2(l), delta);
disp(val);

% Sur la fonction phi2
disp('# Test sur phi2 #');
syms l;
delta = 0.5;
disp(phi2(l, @(l)ns2_1(l), delta));

l = -13;
val = phi2(l, @(l)ns2_1(l), delta);
disp(val);

syms l;
delta = 0.2;
disp(phi2(l, @(l)ns2_2(l), delta));

l = -13;
val = phi2(l, @(l)ns2_2(l), delta);
disp(val);

syms l;
delta = 0.7;
disp(phi2(l, @(l)ns2_2(l), delta));

l = -13;
val = phi2(l, @(l)ns2_2(l), delta);
disp(val);