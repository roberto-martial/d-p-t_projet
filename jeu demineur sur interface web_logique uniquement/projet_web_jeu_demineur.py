#realisé par samncik tong roberto martial et kana Ngoufack rostelle

#date 25/04/2024

#le jeu est fonctionel essayé plusieurs parties en cliquant sur nouvelles parite merci!!!!

import random

s = 0  # cette variable globale servira pour definir la la defaite plus bas

count = 0

k=[]

n=[]

def init(): ##fonction d'initialisation 
    #creation de l'echiquier avec quelque proprité de style

    a="""
    <style>
    .centered {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }

    #container {
        text-align: center;
    }

    #info {
        display: flex;
        justify-content: space-around;
    }

    #end {
        color: red;
        font-weight: bold;
    }

    #jeu {
        margin: 0 auto;
    }
    </style>
    <div id="container">
      <button onclick="start()">Nouvelle partie</button>
      <h6 id="end"></h6>
      <div id="info">
        <h6 id="erreur">erreur:</h6>
        <h6 id="sous">Nombre des sous caché</h6>
        </div>
        <div>
        <h6 ></h6>
        <h6>Nombre des sous caché</h6>
        </div>
      <div id="jeu" class="centered">
      <table id='tab'>
      """
    for i in range(101):
      if i==0:
          a+="""<tr>"""
      elif i%10==0:
          a+="""<td onclick='clic("""+str(i)+""")' id='case"""+str(i)+"""'></td>"""
          a+="""</tr>
                <tr>"""
      else:
          a+="""<td onclick='clic("""+str(i)+""")' id='case"""+str(i)+"""'></td>"""
    a+="""</tr></div></div>"""#a ce niveau la creation de l'echiquier est terminé

    main=document.querySelector("#main")
    main.innerHTML=a #insertion dans le main



#disposition des pieces suivant la logique aleatoire

    l = int(random.random() * 6) + 15 #nombre aleatoire entre 15 et 20

    b = []#liste des cases avec pieces

    v = [] #liste des cases adjacentes à celle ayant les pieces

    new_array = [] 

    d = document.querySelector('#sous')
    d.innerHTML = "nombre de sous caché :"+str(l)


    while len(b) < l:
        random_number = int(random.random() * 99)+1
        while random_number in v or random_number in b:
            random_number = int(random.random() * 99)+1
        if random_number not in v:
            #on add dans v toutes les cases ajacentes à la case choisie
            b.append(random_number)
            if 2 <= random_number <= 9:  # Si le nombre aléatoire est dans la première ligne (2 à 9)
                v.extend([random_number-1, random_number+1, random_number+10, random_number+11])
            elif 92 <= random_number <= 99:  # Si le nombre aléatoire est dans la dernière ligne (91 à 100)
                v.extend([random_number-11, random_number-10, random_number-1, random_number+1])
            elif random_number % 10 == 1:  # Si le nombre aléatoire est dans la première colonne (1 à 91 avec un pas de 10)
                v.extend([random_number-10, random_number+1, random_number+10, random_number+11])
            elif random_number % 10 == 0:  # Si le nombre aléatoire est dans la dernière colonne (10 à 100 avec un pas de 10)
                v.extend([random_number-11, random_number-10, random_number-1, random_number+10])
            else:
                v.extend([random_number-11, random_number-10, random_number-9, random_number-1, random_number+1, random_number+9, random_number+10, random_number+11])
    new_v = []

# Parcourir chaque élément dans v
    for element in v:
        # Si l'élément n'est pas dans b, on ajoute dans à new_v
        if element not in b:
            new_v.append(element)

    # Remplacer v par new_v
    v = new_v

    for i in v :    
        if i< 1 or i>=100:
                v.remove(i) # Supprimer les nombres négatifs et zéro

    for num in v:
        # Vérifier si l'élément a déjà été ajouté à new_array
        already_counted = False
        for item in new_array:
            if item[0] == num:
                item[1] += 1
                already_counted = True
                break
        # Si l'élément n'a pas été trouvé dans new_array
        if not already_counted:
            new_array.append([num, 1])
    # Modifier le contenu des cellules dans la table avec des images
    for i in new_array:
        d = document.querySelector('#case' + str(i[0]))
        d.innerHTML = str(i[1])
    for i in b:
        d = document.querySelector('#case' + str(i))
        d.innerHTML ="<img src='symboles/coste.svg' hidden='hidden'>"
    return b#tres important on recupere la table contenant les les id des pieces utile pour la suite




#definition de quelque variable globale 

k = init() #on recupere la table b 
game_over = False 


#definition des fonction win
def win():
    global game_over
    d = document.querySelector('#end')
    d.innerHTML = "vous avez gagné Le jeu est terminé"
    game_over = True


#definition des fonction defaite
def perdu():
    global game_over
    d = document.querySelector('#end')
    d.innerHTML = "vous avez perdu le jeu"
    game_over = True


replica=[]



#definition de la fonction activant le clic qui gere les clic de joueur
def clic(p):

    global s
    global count
    global k
    global n
    global game_over
    global replica

    if game_over:
        return #le jeu est terminé ici
    

    if p in n:#si l'utilisateur veut cliqué sur une case non clicble
        pass
    else:
        d = document.querySelector('#case' + str(p))
        if p in k:#condition d'affichage des pieces
            d.innerHTML ="<img src='symboles/coste.svg'>"
            n.append(p)
            replica.append(p)# on met dans replica toutes les cases deja trouvées
        else:
            s += 1
            d.innerHTML = ".."#affiche .. lorsque la case est cliqué et ne comporte pas de piece
            y = document.querySelector('#erreur')
            y.innerHTML = "erreur :" + str(s)
            n.append(p)
        if s == 3:#condition de la defaite
            perdu()
        count += 1
    if len(replica) == len(k): # condititon de la victoire 
        win()


#definition de start pour restart une nouvelle partie et initialiser les parametres

def start():#la nouvelle partie initialise tous les parametres

    global s
    global count
    global k
    global n
    global replica
    global game_over
    s = 0
    count = 0
    k = []
    n = []
    replica = []
    game_over = False
    k = init()


start()#pour initialiser des le debut