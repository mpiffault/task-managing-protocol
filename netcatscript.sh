#!/bin/bash


cat << EOF | netcat localhost 8599
CREATE "Une tache" ChefProj1
LIST
CREATE "Une autre tache" ChefProj1
CREATE "Une troisième tache" ChefProj1
LIST
AFF 1 Dev1
AFF 2 Dev2
LIST
DEL 1
LIST
CHSTAT 2 LIB
LIST
AFF 2 AutreDev
LIST
QUIT
EOF
