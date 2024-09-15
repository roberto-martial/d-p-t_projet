#date:2024-03-20
#TP1
#Devoir fait par: Roberto Martial SAMNICK TONG et Rostelle KANA NGOUFACK


adn="TCGACTGCGATCGACAGCCAGCGAAGCCAGCCAGCCGATACCCAGCCAGCCAGCCAGCGAAGCCAGCCAGCCGATACCCAGCCAGCCAGCCAGCGACG\
GCCAGCCAGCCAGCCAGCGAAGCCAGCCAGCCGAGTGCCAGCCAGCCAGCCAGCGAACTGCGATCGACAGCCAGCGAAGCCAGCCAGCCGAATGCCAGCCAGC\
CAGCCAGCGAAGCCAGCCAGCCGATATTCAGCCAGCCAGCCAGCGAACACTCTTCGACAGCCAGCGAAGCCAGCCAGCCGATATTCAGCCAGCCAGCCAGCGA\
ACTCGACACTCTTCGACAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCATCCCAGCGATACCC\
AGCCAGCCAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAGCCAGCGAACTGCGATCGACAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAG\
CCAGCGAACTCGTCTGCGTTCGACAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAGCCAGC\
GATTGCCAGCCAGCCAGCCAGCGAAGCCAGCCAGCCGATTGCCAGCCAGCCAGCCAGCGAACTGCGATCGACAGCCAGCGAAGCCAGCCAGCCGTATGCCAGCC\
AGCATCCCAGCGA"



###1)La fonction antisens prend le brin d'ADN fourni et renvoie le brin d’ADN complémentaire.
def antisens(brinAdn):
    adn_modifie=[]
    for i in range(0,len(brinAdn)):
        if brinAdn[i]=="T":
            adn_modifie.append("A")
        elif brinAdn[i]=="C":
            adn_modifie.append("G")
        elif brinAdn[i]=="G":
            adn_modifie.append("C")
        elif brinAdn[i]=="A":
            adn_modifie.append("T")
    return(''.join(adn_modifie))

###Test unitaire de la fonction antisens
def testantisens():
    assert antisens("AGCATCCCAGCGA")=="TCGTAGGGTCGCT"
    assert antisens("AACATTGT")=="TTGTAACA"
testantisens()

brin_complementaire=antisens(adn) ## à titre de verification
###Lecture en sens inverse(pas demandé mais utile pour la suite de l'exo)
inverse_brin_complementaire=brin_complementaire[::-1]



###2)La fonction trouveDebut recherche tous les codons de départ sur un brin d’ADN et renvoie un tableau contenant les positions du premier nucléotide de chacun des codons
def trouveDebut(brinAdn):
    resultat1 = []
    resultat = []
    i=0
    while i <= len(brinAdn)-3:
        if  brinAdn[i:i+3]=="TAC":
            a=i
            i += 3
            for j in range(i,len(brinAdn)):
                if (brinAdn[j:j+3]=="ATT" and (j-i)%3==0) or (brinAdn[j:j+3]=="ATC" and (j-i)%3==0) or (brinAdn[j:j+3]=="ACT" and (j-i)%3==0): ## est ce que ce que tu as trouvé est multiple de 3
                    resultat.append(brinAdn[a:j+3])##pas demandé mais sort directement le gene plutot que l'indice
                    resultat1.append(a) ##indice TAC
                    break
        else:
            i+=1
    return resultat1
print(trouveDebut(adn))## à titre de verification
print(trouveDebut(inverse_brin_complementaire))## à titre de verification

###Test unitaire de la fonction  trouveDebut
def testtrouveDebut():
    assert trouveDebut("TACTTTATCCCTACGCGA")==[0]
    assert trouveDebut("AACTACACCATTCCTGTACGTAATGACT")==[3,16]
testtrouveDebut()

###3)Même chose que la fonction précédente mais renvoie un tableau avec les positions de tous les codons de terminaison
def trouveFin(brinAdn):
    resultat1 = []
    resultat = []
    i=0
    while i <= len(brinAdn)-3:
        if  brinAdn[i:i+3]=="TAC":
            a=i
            i += 3
            for j in range(i,len(brinAdn)):
                if (brinAdn[j:j+3]=="ATT" and (j-i)%3==0) or (brinAdn[j:j+3]=="ATC" and (j-i)%3==0) or (brinAdn[j:j+3]=="ACT" and (j-i)%3==0):
                    resultat.append(brinAdn[a:j+3])##pas demandé mais sort directement le gene plutot que l'indice
                    resultat1.append(j)##indice fin
                    break
        else:
            i+=1
    return resultat1

###Test unitaire de la fonction  trouveFin
def testtrouveFin():
    assert trouveFin("TACTTTATCCCTACGCGA")==[6]
    assert trouveFin("AACTACACCATTCCTGTACGTAATGACT")==[9,25]
testtrouveFin()

print(trouveFin(adn))## à titre de verification
print(trouveFin(inverse_brin_complementaire))


###4)La fonction trouveGene prend en paramètre un tableau contenant les positions de tous les codons de départ et un autre tableau contenant les positions de tous les codons de terminaison pour un brin
###d’ADN et renvoie un tableau de tuples contenant la liste des gènes (début et fin) trouvés sur un brin.
def trouveGene(debut,fin):
    resultat = []
    for i in range(len(debut)):
        resultat.append((debut[i],fin[i]))
    return resultat

print(trouveGene([38, 74, 402],[311, 311, 720]))## à titre de verification
print(trouveGene([21],[68]))

###Test unitaire de la fonction  trouveGene
def testtrouveGene():
    assert trouveGene([0],[6])==[(0,6)]
    assert trouveGene([3,16],[9,25])==[(3,9),(16,25)]
testtrouveGene()

###5)La fonction transcrire prend en paramètre la sous-chaine de caractère du brin d’ADN débutant au début du gène et se terminant à la fin du gène et renvoie le brin d’ARN correspondant sous forme d’une
#chaine de caractères

def transcrire(gene):
    arn=[]
    for j in range(0,len(gene)):
        if gene[j]=="T":
            arn.append("A")
        elif gene[j]=="C":
            arn.append("G")
        elif gene[j]=="G":
            arn.append("C")
        elif gene[j]=="A":
            arn.append("U")
    return (''.join(arn))

###Test unitaire de la fonction  transcrire
def testtranscrire():
    assert transcrire("TACTTTATCCCTACGCGA"[0:6])=="AUGAAA"
    assert transcrire("AACTACACCATTCCTGTACGTAATGACT"[3:9])=="AUGUGG"
testtranscrire()

print(transcrire(adn[38:311]))## à titre de verification
print(transcrire(adn[74:311]))
print(transcrire(adn[402:720]))
print(transcrire(inverse_brin_complementaire[21:78]))

##6) La fonction traduire rend en paramètre un brin d’ARN (chaine de caractères) et affiche la protéine sous forme d’une chaine de caractères 
def traduire(texte):
    resultat = []
    i = 0
    while i <= len(texte) - 3:
        if texte[i:i + 3] in ["UAA","UAG","UGA"]:   ##codon stop
            break
        elif texte[i:i + 3] in ["AUG"]:
            resultat.append("Méthionine")      ##codon initial
        elif texte[i:i + 3] in ["UUU","UUC"]:
            resultat.append("Phénylalanine")
        elif texte[i:i + 3] in ["UUA","UUG","CUU","CUC","CUA","CUG"]:
            resultat.append("Leucine")
        elif texte[i:i + 3] in ["AUU","AUC","AUA"]:
            resultat.append("Isoleucine")
        elif texte[i:i + 3] in ["GUU","GUC","GUA","GUG"]:
            resultat.append("Valine")
        elif texte[i:i + 3] in ["UCU","UCC","UCA","UCG","AGU","AGC"]:
            resultat.append("Sérine")
        elif texte[i:i + 3] in ["CCU","CCC","CCA","CCG"]:
            resultat.append("Proline")
        elif texte[i:i + 3] in ["ACU","ACC","ACA","ACG"]:
            resultat.append("Thrénine")
        elif texte[i:i + 3] in ["GCU","GCC","GCA","GCG"]:
            resultat.append("Alanine")
        elif texte[i:i + 3] in ["UAU","UAC"]:
            resultat.append("Tyrosine")
        elif texte[i:i + 3] in ["CAU","CAC"]:
            resultat.append("Histidine")
        elif texte[i:i + 3] in ["CAA","CAG"]:
            resultat.append("Glutamine")
        elif texte[i:i + 3] in ["AAU","AAC"]:
            resultat.append("Asparagine")
        elif texte[i:i + 3] in ["AAA","AAG"]:
            resultat.append("Lysine")
        elif texte[i:i + 3] in ["GAU","GAC"]:
            resultat.append("Aspartate")
        elif texte[i:i + 3] in ["GAA","GAG"]:
            resultat.append("Glutamate")
        elif texte[i:i + 3] in ["UGU","UGC"]:
            resultat.append("Cystéine")
        elif texte[i:i + 3] in ["UGG"]:
            resultat.append("Tryptophane")
        elif texte[i:i + 3] in ["CGU","CGC","CGA","CGG","AGA","AGG"]:
            resultat.append("Arginine")
        elif texte[i:i + 3] in ["GGU","GGC","GGG","GGA"]:
            resultat.append("Glycine")
        i+=3
    return('-'.join(resultat))

###Test unitaire de la fonction  traduire
def testtraduire():
    assert traduire("AUGAAU")=="Méthionine-Asparagine"
    assert traduire("AUGUGG")=="Méthionine-Tryptophane"
testtraduire()

acideamine1=traduire(transcrire(adn[38:311]))## à titre de verification
acideamine2=traduire(transcrire(adn[74:311]))
acideamine3=traduire(transcrire(adn[402:720]))
acideamine4=traduire(transcrire(inverse_brin_complementaire[21:78]))


print(acideamine1)
print(acideamine2)
print(acideamine3)
print(acideamine4)


##La fonction prot est une fonction intermédiaire qui prend en parametre la protéine trouvée et affecte à chaque codon de cette dernière, une lettre
def prot(proteine):
    resultat=[]
    pro=proteine.split("-")
    for i in range(0,len(pro)):
        if pro[i]=="Méthionine":
            resultat.append("M")
        elif pro[i]=="Phénylalanine":
            resultat.append("F")
        elif pro[i]=="Leucine":
            resultat.append("L")
        elif pro[i]=="Isoleucine":
            resultat.append("I")
        elif pro[i]=="Valine":
            resultat.append("V")
        elif pro[i]=="Sérine":
            resultat.append("S")
        elif pro[i]=="Proline":
            resultat.append("P")
        elif pro[i]=="Thrénine":
            resultat.append("T")
        elif pro[i]=="Alanine":
            resultat.append("A")
        elif pro[i]=="Tyrosine":
            resultat.append("Y")
        elif pro[i]=="Histidine":
            resultat.append("H")
        elif pro[i]=="Glutamine":
            resultat.append("Q")
        elif pro[i]=="Asparagine":
            resultat.append("N")
        elif pro[i]=="Lysine":
            resultat.append("K")
        elif pro[i]=="Aspartate":
            resultat.append("D")
        elif pro[i]=="Glutamate":
            resultat.append("E")
        elif pro[i]=="Cystéine":
            resultat.append("C")
        elif pro[i]=="Tryptophane":
            resultat.append("W")
        elif pro[i]=="Arginine":
            resultat.append("R")
        elif pro[i]=="Glycine":
            resultat.append("G")
    return resultat

proteine1=prot(acideamine1)
proteine2=prot(acideamine2)
proteine3=prot(acideamine3)
proteine4=prot(acideamine4)
proteine=[proteine1,proteine2,proteine3,proteine4]##tableau (de tableaux) qui contient toutes les protéines

from turtle import *
from  math import sqrt

##7) La fonction carre prend deux entiers en paramètre (taille du côté du carré et l’indice du carré à dessiner) et trace un carré à l’aide de la tortue
def carre(x,a):
    for _ in range(0,4):
        fd(a)
        lt(90) 
    fd(a)
    lt(135)
    pu()
    fd((a*(sqrt(2)))/2)
    pd()
    rt(135)
    write(x)
    rt(-135)
    #fin dessin de la lettre
    lt(180)
    pu()
    fd((a*(sqrt(2)))/2)
    #down()
    lt(45)
    pd()
    #fin de la mise au pas pour le prochain dessin
#mise en place des conditions de depart de la tortue


pu()
goto(-170,100) #position de depart
pd()
#fin de la mise au pas pour le prochain dessin

#mise ne place des conditions de depart de la tortue


#fonction cycle est une fonction intermédiaire utilisée pour le tracé d'une seule chaine de proteine
#elle prend en parametre la taille des carrés et la chaine de proteine visée


def cycle(t,taille_carre,proteine) :
    i=0
    d=0
    nb_par_ligne=15 #nombre d'element sur la ligne
    for lettre in  proteine :
            carre(lettre,taille_carre)
            i+=1
            if i==nb_par_ligne :
                pu()
                goto(t[-2],t[-1])
                lt(-90)
                fd(d+taille_carre)
                pd()
                d+=taille_carre
                lt(90)
                i=0

#execution du test de la fonction cycle cas de proteine1
#t=[0,0]
#cycle(t,30,proteine1)


#la fonction repostion prend en paramètres un tableau, une protéine et la taille des carrés 
#elle recalcule dynamiquement la nouvelle position de la seconde chaine de proteine
                

def reposition(a,proteine,taille_carre):
    if (len(proteine)%15)==0:#le cas ou on a pas de debordement de carre
        t1=a[-2]
        t2=a[-1]-((len(proteine)//15)*taille_carre+5)           
        j=[t1,t2]
        return j
    else:
        t1=a[-2]
        t2=a[-1]-((len(proteine)//15)*taille_carre+taille_carre+5)           
        j=[t1,t2]
        return j
         

#test de reposition
#cette fonction n'aura pas de test et sera directement utilisé dans la suite 


#la fonction trace_entier tracera toutes les chaines 
#elle prend en parametre la taille du carré et la table contenant toutes les proteines

def trace_entier(taille_carre,proteines):
        p=[-170,100]
        for i in range(len(proteines)) :#trace le premier bloc
            t=proteine[i]
            if i==0:
                cycle(p,taille_carre,t)
            else:#trace le reste de blocs
                u=p
                j=reposition(u,proteine[i-1],taille_carre)
                print(j)
                pu()
                goto(j[-2],j[-1])
                pd()
                cycle(j,taille_carre,t)
                p=j
                 
#test finaux

#mise en place des parametre de test
#speed(500)
pu()
goto(-170,100) #position de depart
pd()

#tracé du de toutes les grilles

trace_entier(9,proteine)

