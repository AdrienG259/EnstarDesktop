//package bibliotheque;
//
//import java.io.File;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class SaisieNouveauDocument {
//
//    public static void main(String[] args) {
//        File fichier = new File("MaBibliotheque.txt");
//        System.out.println("Lecture du fichier "+fichier.getAbsolutePath());
//        Bibliotheque maBibliotheque = new Bibliotheque(10,fichier);
//
//        while (true) {
//            System.out.print("Saisie d'un nouveau document [R-Roman] [M-Manuel] [U-RevUe] [D-Dictionnaire] [Q-Sauver et Quitter] : ");
//            Scanner scanner = new Scanner(System.in);
//            String reponse = scanner.nextLine();
//            if (reponse.startsWith("Q")) {
//                maBibliotheque.sauver(fichier);
//                System.out.println("Arret de l'application");
//                System.exit(0);
//            }
//            else if (reponse.startsWith("R")) {
//                Roman nouveauRoman = new Roman(
//                        genererNouvelIdentifiant(maBibliotheque),
//                        saisieChaine("Titre:"),
//                        saisieChaine("Auteur:"),
//                        saisieEntier("Nombre de pages:"),
//                        saisiePrixLitteraire()
//                );
//                maBibliotheque.ajouter(nouveauRoman);
//            }
//            else if (reponse.startsWith("M")) {
//                Manuel nouveauManuel = new Manuel(
//                        genererNouvelIdentifiant(maBibliotheque),
//                        saisieChaine("Titre:"),
//                        saisieChaine("Auteur:"),
//                        saisieEntier("Nombre de pages:"),
//                        saisieNiveau()
//                );
//                maBibliotheque.ajouter(nouveauManuel);
//            }
//            else if (reponse.startsWith("U")) {
//                Revue nouvelleRevue = new Revue(
//                        genererNouvelIdentifiant(maBibliotheque),
//                        saisieChaine("Titre:"),
//                        saisieEntier("Mois:"),
//                        saisieEntier("Année:")
//                );
//                maBibliotheque.ajouter(nouvelleRevue);
//            }
//            else if (reponse.startsWith("D")) {
//                Dictionnaire nouveauDictionnaire = new Dictionnaire(
//                        genererNouvelIdentifiant(maBibliotheque),
//                        saisieChaine("Titre:"),
//                        saisieLangue()
//                );
//                maBibliotheque.ajouter(nouveauDictionnaire);
//            }
//            else {
//                System.out.println("Commande inconnue");
//            }
//        }
//    }
//
//    private static String saisieChaine(String question) {
//        System.out.print(question);
//        return (new Scanner(System.in)).nextLine();
//    }
//
//    private static int saisieEntier(String question) {
//        while (true) {
//            System.out.print(question);
//            try {
//                return (new Scanner(System.in)).nextInt();
//            }
//            catch (InputMismatchException e) {
//                System.out.println("La réponse doit être un entier");
//            }
//        }
//    }
//
//    private static Roman.PrixLitteraire saisiePrixLitteraire() {
//        String saisie = saisieChaine("Prix Littéraire ? [G]oncourt [M]edicis [I]nterallie [A]ucun :");
//       if (saisie.startsWith("G")) {
//           return Roman.PrixLitteraire.GONCOURT;
//       }
//       else if (saisie.startsWith("M")) {
//            return Roman.PrixLitteraire.MEDICIS;
//        }
//       else if (saisie.startsWith("I")) {
//           return Roman.PrixLitteraire.INTERALLIE;
//       }
//       else {
//           return null;
//       }
//    }
//
//    private static Manuel.Niveau saisieNiveau() {
//        String saisie = saisieChaine("Niveau ? [E]cole élementaire [C]ollège [L]ycée :");
//        if (saisie.startsWith("E")) {
//            return Manuel.Niveau.ECOLE_ELEMENTAIRE;
//        }
//        else if (saisie.startsWith("C")) {
//            return Manuel.Niveau.COLLEGE;
//        }
//        else if (saisie.startsWith("L")) {
//            return Manuel.Niveau.LYCEE;
//        }
//        else {
//            return null;
//        }
//    }
//
//    private static Dictionnaire.Langue saisieLangue() {
//        String saisie = saisieChaine("Langue ? [F]rançais [A]nglais [D]Allemand :");
//        if (saisie.startsWith("A")) {
//            return Dictionnaire.Langue.ANGLAIS;
//        }
//        else if (saisie.startsWith("D")) {
//            return Dictionnaire.Langue.ALLEMAND;
//        }
//        else if (saisie.startsWith("F")) {
//            return Dictionnaire.Langue.FRANCAIS;
//        }
//        else {
//            return null;
//        }
//    }
//
//    private static int genererNouvelIdentifiant(BibliothequeAbstraite<?> bib) {
//        int nouvelIdentifiant = 1;
//        for (Document doc : bib.getDocuments()) {
//            if (doc.getNumEnreg() >= nouvelIdentifiant) {
//                nouvelIdentifiant = doc.getNumEnreg()+1;
//            }
//        }
//        return nouvelIdentifiant;
//    }
//}
