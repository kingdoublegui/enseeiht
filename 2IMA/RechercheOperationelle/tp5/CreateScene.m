%
lig = input('*** Donner le nombre de lignes de la scene :','s');
lig= str2double(lig);
nlig=int16(lig);
col= input('*** Donner le nombre de colonnes de la scene :','s');
col= str2double(col);
ncol=int16(col);
im= ones(nlig,ncol,'int16'); % initialisation de im a 1
%
im(1:2,1:ncol) = 7; % les 2 premieres lignes a 7
im(nlig-1:nlig,1:ncol) = 7; % les 2 dernieres lignes a 7
im(1:nlig,1:2) = 7;  % les 2 premieres colonnes a 7
im(1:nlig,ncol-1:ncol) = 7; % les 2 dernieres colonnes a 7
%
hscene = figure('BackingStore','on');
colormap(jet);
imagesc(im);
%
button = 0;
while button ~= 3
    disp ('*** Clic gauche sur un coin de l''obstacle à creer (si fin creation clic droit) ***');
    [p(1),q(1),button] = ginput(1);
    if button ~= 3
        disp ('*** Clic gauche sur un autre coin de l''obstacle à creer ***');
        [p(2),q(2),button] = ginput(1);
        % Get the x and y corner coordinates as integers
        sp(1) = min(round(p(1)), round(p(2))); %xmin
        sp(2) = min(round(q(1)), round(q(2))); %ymin
        sp(3) = max(round(p(1)), round(p(2)));   %xmax
        sp(4) = max(round(q(1)), round(q(2)));   %ymax
        % Index into the original image to create the new image
        im(sp(2):sp(4), sp(1): sp(3)) = 7;
        imagesc(im);
    end
end
