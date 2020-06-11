# e-boutique
Une simulation de "e-boutique" en standalone ;) JAVA/SWING/MAVEN/NETBEANS

For english version see bellow.

Bonjour !

Ceci est un projet de fin d'étude pour lequel nous n'avions que 3 jours. J'ai néanmoins décidé de le continuer afin de le terminer. A l'heure où j'écris, il est toujours en cours d'écriture. Pour l'instant, il n'est utilisable que sur windows, car la création des dossiers de ressources (profil, et produit dans l'avenir), se font via avec l'arbre des dossiers windows (appData etc).
Peut-être ferais-jee une version pour mac à l'avenir.

Où j'en suis ?

Pour l'instant, la struture du projet est stable, la plupart des méthodes "back-end" sont écrites. Toute la partie utilisateur (création de compte, modification, upload d'une photo de profil, login, check du login etc.) est faite.

L'interface utilisateur est faite également. Il manque (mais je le ferai à la fin) la définition des rôles : Admin, user, employé.

Il me reste donc toute la partie "boutique" à proprement parler, plus d'autres choses :

- Création des produits (id du produit, son prix, sa photo etc.). 
- Affichage des produits. Affichage de tous les produits ainsi que les produits par catégorie.
- La difficulté principale, reste donc l'affichage desdits produits, automatiser cette tâche dans la fenêtre qui sera dévolue à cet effet. Un travail assez long en perspective.
- Ensuite, gros morceau, tous ce qui est en rapport avec l'achat du produit : la partie DAO est fait, mais l'implémentation graphique non. Il s'agira donc de pouvoir faire en sorte de consulter son "panier" et de passer une commande. Bien sûr il s'agit d'une simulation, donc on ne pourra pas "payer" la commande et pas non plus la recevoir :0)
- La partie REGEX : partie que personnellement je préfère faire à la fin car elle ne concerne pas le fonctionnement du logiciel en lui même. Si certaine restriction sont déjà implémenté, il en reste pas mal à faire.
- Sécurisation du tout : j'ai déjà sécurisé le stockage des mots de passe, mais je vais probablement changer la méthode de cryptage (Cryptage DES) pour une plus sûre avec du "sel". Pour l'instant, la ségrégation des interfaces et tout le toutim me semble ok.
- Des trucs que j'ai oubliés, des fonctionnalité qui viendront plus tard.
- Si j'ai le courage d'abattre autant de travail, faire une couche web via REST.

A NOTER :

La partie graphique a été faite en SWING via le GUI Form de netbeans. Cela induit quelques changements dans la marche à suivre, qui est différente de celle d'Eclipse. En effet, Netbeans crée tout un tas de code généré automatiquement.

Je sais, ça n'est pas très propre. Mais ayant commencé avec ça, je terminerai avec ça. Cela veut dire qu'en général, dans la partie Vue, les listeners font appel à la partie "action" via des classes abstraites (dans le package Vue, toutes les classes "Action") qui font appel aux classes "Control" dans le package "Control" qui font elle même appel aux classes nécessaires (de cryptage, DAO, etc.).

Cela peut-être assez perturbant, donc je préfère le souligner. Cependant, avec mes connaissances de base, cette structure me semble assez solide et se révèle la plus pratique à utiliser (mais peut-être y en a-t-il d'autres).

En somme, le projet se décompose comme suit :

- Modèle : les packages concernant les entités (utilisateur, produit, cb, commandes etc).

- Vue : avec Swing. S'il y a des méthodes (private) dans la classe de l'applicaiton, c'est que celles-ci ne concernent QUE la vue en tant que telle.
Autrement dit, les traitements qu'il y a sont là pour organiser la partie graphique, pas pour autre chose. Si traitement de données (création lecture etc), c'est via des classes abstraites (action dans le package Vue) et leurs méthodes qui font elles mêmes appel à la partie Controleur. Pourquoi des classes abstraites ? Pour une raison de lisibilité du code et de sa sécurisation !

- Contrôleur : tous les traitements liés à la lecture et l'écriture des données passent par cette couche là (packages de la DAO, principalement).

Vous pouvez réutiliser ce projet ainsi que tout le code que j'ai écrit comme bon vous semble. Il se peut que des choses (images etc) soient soumises au droit d'auteur. Si vous souhaitez installer le projet, je vous conseille :

- 1 D'installer la base de données. Si vous ne savez pas comment faire, les moteurs de recherche sont là pour vous.
- 2 De "nettoyer" cette base de donnée. En effet, il doit y avoir un ou deux comptes de présents, mais comme vous vous en doutez, les photos de profil de ces comptes ne sont pas présentes : cela pourrait faire quelques bugs.
- 3 De commencer par vous créer un compte afin de remplir la base de données et de voir ce que pour l'instant le logiciel propose (AKA, la partie utilisateur pour l'instant).
- N'oubliez pas qu'il peut y avoir des soucis avec l'accès à la base de données ! Donc vérifiez si l'ip et tout le toutim est correctement renseignées dans le fichiers "dbfile.properties" dans la package "BDD".


Si vous souhaitez me contacter : chebadad@lilo.org .

English Version :

Hello !

This is an end of study project for which we only had 3 days. I nevertheless decided to continue it in order to finish it. At the time of writing, it is still being written. For now, it can only be used on windows, because the creation of resource folders (profile, and product in the future), are done via the windows folder tree (appData etc).
Maybe I will make a version for mac in the future.

Where I am ?

For the moment, the project structure is stable, most of the "back-end" methods are written. All the user part (account creation, modification, upload of a profile picture, login, login check etc.) is done.

The user interface is also made. The definition of roles is missing (but I will do it at the end): Admin, user, employee.

So I have the whole "shop" part proper, plus other things:

- Creation of products (product id, price, photo etc.).
- Display of products. Display of all products as well as products by category.
- The main difficulty, therefore remains the display of said products, automating this task in the window which will be used for this purpose. A fairly long job in perspective.
- Then, big chunk, all that is related to the purchase of the product: the CAD part is done, but the graphic implementation not. It will therefore be a question of being able to consult your "basket" and place an order. Of course it is a simulation, so we will not be able to "pay" for the order and not receive it either: 0)
- The REGEX part: part that I personally prefer to do at the end because it does not concern the operation of the software itself. If certain restrictions are already implemented, there is still a lot to do.
- Securing everything: I have already secured the storage of passwords, but I will probably change the encryption method (DES encryption) to a more secure one with "salt". For now, the segregation of interfaces and everything else seems to me ok.
- Stuff that I forgot, features that will come later.
- If I have the courage to do so much work, do a web layer via REST.

TO NOTE :

The graphic part was made in SWING via the GUI Form of netbeans. This induces some changes in the procedure to follow, which is different from that of Eclipse. Indeed, Netbeans creates a whole bunch of automatically generated code.

I know, it's not very clean. But having started with that, I will end with that. This means that in general, in the View part, the listeners call the "action" part via abstract classes (in the View package, all the "Action" classes) which use the "Control" classes in the "Control" package which itself uses the necessary classes (encryption, DAO, etc.).

This can be quite disturbing, so I prefer to point it out. However, with my basic knowledge, this structure seems pretty solid and proves to be the most practical to use (but maybe there are others).

In summary, the project breaks down as follows:

- Model: the packages concerning the entities (user, product, cb, orders, etc.).

- View: with Swing. If there are (private) methods in the class of the application, it is because they only concern the view as such.
In other words, the processing there is there to organize the graphic part, not for anything else. If data processing (creation, reading, etc.), it is via abstract classes (action in the View package) and their methods which themselves call on the Controller part. Why abstract classes? For a reason of readability of the code and its security!

- Controller: all the processing related to reading and writing data goes through this layer there (mainly CAD packages).

You can reuse this project as well as all the code I wrote as you see fit. It may be that things (images etc) are subject to copyright. If you want to install the project, I advise you:

- 1 To install the database. If you don't know how to do it, the search engines are there for you.
- 2 To "clean up" this database. Indeed, there must be one or two accounts present, but as you can imagine, the profile photos of these accounts are not present: this could cause some bugs.
- 3 To start by creating an account in order to fill the database and see what the software currently offers (AKA, the user part for the moment).
- Do not forget that there may be problems with access to the database! So check if the ip and all toutim is correctly filled in the "dbfile.properties" files in the "BDD" package.

If you want to contact me : chebadad@lilo.org .
