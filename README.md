# README - Application de la Communauté d'Agglomération

Bienvenue dans l'application de la Communauté d'Agglomération! Cette application est écrite en Java et vous permet de gérer diverses fonctionnalités liées à la communauté d'agglomération. 

## Résumé du Projet
Avec la généralisation des véhicules électriques, il est important d’installer des bornes de recharge de véhicules électriques accessibles, mais sans en installer trop (ce qui augmente le coût global de leur installation et entretien). On suppose que le responsable de l’aménagement d’une communauté d’agglomération fait appel à nous pour réaliser un logiciel qui permettrait de déterminer quelles villes dans la communauté doivent accueillir un parking équipé de bornes de recharge. Ses contraintes sont les suivantes :
- (Accessibilité) Chaque ville doit posséder ses bornes, ou être directement reliée à une ville qui possède des bornes.
- (Économie) Le coût du projet doit être le plus bas possible, ce qui signifie que le nombre de bornes à construire doit être le plus petit possible.

La mission qui nous est confiée, est de développer un logiciel qui permet :
1) de représenter les villes d’une communauté d’agglomération, et les routes qui les relient;
2) de simuler la construction de parkings équipés de bornes de recharge dans les villes de la communauté;
3) de s’assurer que la contrainte d’accessibilité est respectée;
4) de calculer le coût d’une solution (le nombre zones de recharge à construire),
5) de minimiser ce coût.


## Installation d'un IDE 

1. Vous devez au préalable avoir un IDE qui peut compiler du code Java, tel que Visual Studio Code ou encore Eclipse.
2. Installer une version de Java sur votre ordinateur depuis le site officiel
3. Si vous utilisez Visual Studio Code vous pouvez utiliser l'extension "Extension Pack for Java", pour vous faciliter.

## Télécharger le projet

N'oubliez pas de télécharger le projet et de le dezipper si il est en .zip !

## Exécution dans l'IDE de votre choix

Vous pouvez également exécuter cette application dans l'IDE de votre choix en suivant des étapes similaires à celles ci-dessus, en utilisant les fonctionnalités d'exécution Java de votre IDE.

## Exécution depuis un terminal

Si vous préférez utiliser un terminal pour exécuter l'application, voici comment procéder:

### 1\ Depuis le terminal de Visual Studio Code

1. Lancez une nouvelle fenêtre de Visual Studio Code et cliquez sur ouvrir un dossier
2. Ouvrez le projet que vous aurez préalablement télécharger
3. Ouvrez un terminal de commande de Visual Studio Code manuellement ou en exécutant les touches Ctrl+Shift+ù
4. Vous serez verrez normalement le chemin du projet que vous venez d'ouvrir, voici ce que devrait s'afficher :

"PS C:\Users\votre\chemin\ProjetCommunauteAgglomeration"

5. Compilez le code source en utilisant la commande suivante:
```bash
javac -encoding UTF-8 src\Main.java
```

6. Une fois la compilation terminée sans erreurs, exécutez l'application en utilisant la commande:
```bash
java src\Main
```
ou alors 
```bash
java src\Main.java
```

### 2\ Depuis le terminal de votre choix

1. Lancez un nouveau terminal
2. Vous devez utiliser la commande cd `cd` pour vous dirigez jusqu'au chemin du projet que vous avez téléchargé.
Comme ceci : 
```bash
cd chemin\vers\le\projet\ProjetCommunauteAgglomeration
```
3. Compilez le code source en utilisant la commande suivante:
```bash
javac -encoding UTF-8 src\Main.java
```
4. Une fois la compilation terminée sans erreurs, exécutez l'application en utilisant la commande:
```bash
java src\Main.java
```
ou alors 
```bash
java src\Main
```

### 3\ Depuis un script

Vous pouvez double cliquer sur executeMain.bat depuis votre dossier, ou faire clique droit et "RunCode" sur Visual Studio Code ! 
Vous pouvez également le faire depuis un terminal en exécutant cette commande ( vérifiez bien le bon fichier )
```bash
executeMain.bat
```

## Fonctionnalités Implémentées

1. **Création des Villes et de la Communauté** : Vous pouvez créer des villes et les ajouter à la communauté

2. **Gestion des Routes** : Notre logiciel permet d'ajouter ou de supprimer des routes entre les villes pour modéliser les connexions.

3. **Gestion des Zones de Recharge** : Vous pouvez ajouter ou supprimer des zones de recharge dans les villes.

4. **Affichage Convivial** : Nous avons conçu une interface utilisateur agréable et conviviale pour vous permettre de visualiser facilement l'état de la communauté, y compris les villes, les routes et les zones de recharge.

5. **Algorithme d'Optimisation** : Notre logiciel intègre un algorithme qui calcule la solution optimale pour respecter les contraintes d'accessibilité (chaque ville doit avoir ses bornes ou être reliée à une ville avec des bornes) et d'économie (minimiser le nombre de bornes à construire).
Si vous téléchargez un fichier pour créer votre communauté d'agglomération, et que vous choisissez d'appliquer la solution automatique pour ajouter les zones de recharge de manière optimale, cela est possible si et seulement si votre fichier de communauté initial respecte les contraintes d'accessibilité. Sinon, la solution automatique appliquera une solution naïve (une zone de recharge dans chaque ville). Si vous appliquez la solution automatique une deuxième fois, la solution optimale sera alors appliquée, car la contrainte est respectée cette fois-ci.

6. **Lecture de Fichiers** : Vous pouvez importer des données de villes, de routes et de zones de recharge à partir de fichiers pour simplifier la création de votre communauté.

7. **Sauvegarde dans une Base de Données** : Nous avons également ajouté la possibilité de sauvegarder l'état de votre communauté dans un fichier pour une utilisation future.

8. **Récapitulatif à la Fin** : À la fin de l'exécution du programme, un récapitulatif vous sera présenté, affichant l'état de la communauté d'agglomération.

9. **Gestion des Erreurs** : Nous avons pris soin de gérer un maximum d'erreurs utilisateur pour garantir une meilleure expérience .

# Bonus : Affichage JavaFx

Nous avons également intégré une interface utilisateur JavaFX qui enrichit l'expérience de l'utilisateur en offrant des fonctionnalités graphiques intuitives. Cette interface vous permet de :

1. **Visualiser des données depuis des fichiers existants ou de créer une nouvelle communauté.**
2. **Afficher et créer des routes entre les villes pour modéliser les connexions.**
3. **Afficher et ajouter des zones de recharge pour les villes.**
4. **Appliquer un algorithme optimal pour respecter les contraintes d'accessibilité et d'économie.**
5. **Visualiser la solution calculée pour l'optimisation.**
6. **Sauvegarder l'état actuel de la communauté dans un dossier pour une utilisation future.**


JavaFX offre une expérience utilisateur interactive et visuelle qui facilite la gestion de votre communauté d'agglomération

## Pour utiliser JavaFx

Vous pouvez utiliser notre interface JavaFX pour profiter pleinement de l'expérience utilisateur enrichie. Voici trois scénarios pour utiliser JavaFX :

### Scénario 1 : Utilisation du Fichier .bat

Dans le projet que vous avez téléchargé "ProjetCommunauteAgglomeration", vous trouverez un Execute.bat, il vous suffit de l'exécuter en double cliquant dessus, que vous soyez depuis votre bureau ou depuis Visual Studio Code.

Sinon lancer le terminal de votre choix, assurez vous bien d'être dans le chemin grâce aux commandes `cd` :
```bash
cd chemin\vers\le\projet\ProjetCommunauteAgglomeration
```

Exécuter le .bat depuis le terminal : 

```bash
Execute.bat
```

### Scénario 2 : Téléchargement à partir du Répertoire GitHub (si scénario 1 fonctionne pas)

1. Allez sur ce lien : [JavaFx GitHub](https://github.com/ArbaneReda/ApplicationJavaFx)
2. Télécharger ce dossier et dézipper le fichier
3. Double cliquez sur le Execute.bat ou clique droit "Run Code"
4. Ou le faire manuellement depuis un terminal (même principe il faut s'assurer d'être dans le chemin grâce aux commandes `cd`, puis `Execute.bat`)

### Fonctionnalités Implémentées graphiquement

1. **Création des Villes et de la Communauté** : Vous pouvez créer des villes et les ajouter à la communauté

2. **Gestion des Routes** : Notre logiciel permet d'ajouter ou de supprimer des routes entre les villes 

3. **Gestion des Zones de Recharge** : Vous pouvez ajouter ou supprimer des zones de recharge dans les villes.

4. **Affichage Convivial avec JavaFX** : Nous avons conçu une interface utilisateur basée sur JavaFX, offrant une expérience visuelle intuitive pour visualiser l'état de la communauté, y compris les villes, les routes et les zones de recharge.

5. **Lecture depuis un Fichier** : Vous pouvez importer des données de villes, de routes et de zones de recharge à partir de fichiers pour simplifier la création de votre communauté.

6. **Sauvegarde de l'État de la Communauté** : Vous avez la possibilité de sauvegarder l'état actuel de la communauté dans un fichier pour une utilisation future.

7. **Algorithme Automatique d'Optimisation** : Notre logiciel intègre un algorithme qui calcule automatiquement la solution optimale pour respecter les contraintes d'accessibilité.




# Auteur

Ce projet a été développé par :

 * [Arbane](https://github.com/ArbaneReda).
 * [Alashour](https://github.com/AkramChaabnia).
 * [Chaabnia](https://github.com/ahmadalashour).

Merci de votre lecture, et bon test à vous !

**L'équipe de développement**
