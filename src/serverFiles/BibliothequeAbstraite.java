//package bibliotheque;
//
//import bibliotheque.Document;
//import bibliotheque.Livre;
//
//import java.io.*;
//import java.util.*;
//
//public abstract class BibliothequeAbstraite<D extends Document> {
//
//    private final List<D> documents;
//
//    public BibliothequeAbstraite(int capacite) {
//        documents = new ArrayList<>(capacite);
//    }
//
//    protected void initWith(File aFile) {
//        BufferedReader reader = null;
//        try {
//            reader = new BufferedReader(
//                    new InputStreamReader(new FileInputStream(aFile), "UTF-8"));
//            String line = reader.readLine();
//            while (line != null) {
//                D nouveauDocument = (D) creerUnNouveauDocumentAPartirDeLaLigne(line);
//                if (nouveauDocument != null) {
//                    ajouter(nouveauDocument);
//                }
//                line = reader.readLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // Quel que soit ce qui se passe, on passera toujours ici
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    // solution d'Alexandre
//    private Map<String, String> ligneVersData(String ligne) {
//        Map<String, String> data = new HashMap<>();
//        try {
//            String[] paires = ligne.split(",");
//            for (String e : paires) {
//                String[] keyValue = e.split("=");
//                data.put(keyValue[0], keyValue[1]);
//            }
//        } catch (Exception e) {
//            System.out.println("[-] Erreur le fichier est malformé !");
//            System.out.println("-- Erreur à la ligne : " + ligne);
//        }
//        return data;
//    }
//
//
//    private Document creerUnNouveauDocumentAPartirDeLaLigne(String ligne) {
//        //System.out.println(ligne);
//        if (ligne != null) {
//            StringTokenizer st = new StringTokenizer(ligne, ",");
//            Map<String, String> attributsValeursLues = new HashMap<>();
//            while (st.hasMoreTokens()) {
//                String attributValeurLue = st.nextToken();
//                if (attributValeurLue.contains("=")) {
//                    StringTokenizer st2 = new StringTokenizer(attributValeurLue, "=", false);
//                    String attribut = st2.nextToken();
//                    String valeur = st2.nextToken();
//                    attributsValeursLues.put(attribut, valeur);
//                    //System.out.println(attribut + " = " + valeur);
//                }
//            }
//            if (attributsValeursLues.get("type") != null) {
//                if (attributsValeursLues.get("type").equals("Roman")) {
//                    return new Roman(
//                            obtenirEntier(attributsValeursLues.get("id")),
//                            obtenirChaine(attributsValeursLues.get("titre")),
//                            obtenirChaine(attributsValeursLues.get("auteur")),
//                            obtenirEntier(attributsValeursLues.get("nbrPages")),
//                            obtenirPrixLitt(attributsValeursLues.get("prixLitt")));
//                } else if (attributsValeursLues.get("type").equals("Manuel")) {
//                    return new Manuel(
//                            obtenirEntier(attributsValeursLues.get("id")),
//                            obtenirChaine(attributsValeursLues.get("titre")),
//                            obtenirChaine(attributsValeursLues.get("auteur")),
//                            obtenirEntier(attributsValeursLues.get("nbrPages")),
//                            obtenirNiveau(attributsValeursLues.get("niveau")));
//                } else if (attributsValeursLues.get("type").equals("Revue")) {
//                    return new Revue(
//                            obtenirEntier(attributsValeursLues.get("id")),
//                            obtenirChaine(attributsValeursLues.get("titre")),
//                            obtenirEntier(attributsValeursLues.get("mois")),
//                            obtenirEntier(attributsValeursLues.get("annee")));
//                } else if (attributsValeursLues.get("type").equals("Dictionnaire")) {
//                    return new Dictionnaire(
//                            obtenirEntier(attributsValeursLues.get("id")),
//                            obtenirChaine(attributsValeursLues.get("titre")),
//                            obtenirLangue(attributsValeursLues.get("langue")));
//                } else {
//                    System.err.println("Impossible de construire un Document, type inconnu: " + attributsValeursLues.get("type"));
//                    return null;
//                }
//            } else {
//                System.err.println("Impossible de construire un Document sans son type");
//                return null;
//            }
//        }
//        System.err.println("Impossible de construire un Document avec une ligne vide");
//        return null;
//
//    }
//
//    private int obtenirEntier(String chaine) {
//        return Integer.parseInt(chaine);
//    }
//
//    private String obtenirChaine(String chaine) {
//        // On enleve les guillemets
//        if (chaine.startsWith("\"") && chaine.endsWith("\"")) {
//            return chaine.substring(1, chaine.length() - 1);
//        }
//        return chaine;
//    }
//
//    private Roman.PrixLitteraire obtenirPrixLitt(String chaine) {
//        if (chaine != null) {
//            return Roman.PrixLitteraire.valueOf(chaine);
//        }
//        return null;
//    }
//
//    private Manuel.Niveau obtenirNiveau(String chaine) {
//        if (chaine != null) {
//            return Manuel.Niveau.valueOf(chaine);
//        }
//        return null;
//    }
//
//    private Dictionnaire.Langue obtenirLangue(String chaine) {
//        if (chaine != null) {
//            return Dictionnaire.Langue.valueOf(chaine);
//        }
//        return null;
//    }
//
//    public void sauver(File fichier) {
//        System.out.println("Sauvegarde du fichier " + fichier);
//
//        FileWriter writer = null;
//        BufferedWriter bw = null;
//        try {
//            writer = new FileWriter(fichier);
//            bw = new BufferedWriter(writer);//pw = new PrintWriter(w)
//            for (D document : documents) {
//                bw.write(document.serialiser() + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally { //un bloc finally est toujours exécuté
//            try {
//                if (bw != null)
//                    bw.close(); //pour arrêter la mémoire tampon
//                if (writer != null)
//                    writer.close(); //ferme le flux et libère resour.
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        for (D document : documents) {
//            System.out.println(document.serialiser());
//        }
//    }
//
//    public boolean ajouter(D doc) {
//        return documents.add(doc);
//    }
//
//    public boolean supprimer(D doc) {
//        return documents.remove(doc);
//    }
//
//    public D document(int i) {
//        if (i >= 0 && i < documents.size()) {
//            return documents.get(i);
//        }
//        System.err.println("Index de document non valide: " + i);
//        return null;
//    }
//
//    public List<D> getDocuments() {
//        return documents;
//    }
//    public int getNombreDeDocuments() {
//        return documents.size();
//    }
//
//    public void afficherDocuments() {
//        for (D doc : documents) {
//            System.out.println(doc.toString());
//        }
//    }
//
//    public void afficherAuteurs() {
//        for (D doc : documents) {
//            if (doc instanceof Livre) {
//                System.out.println(((Livre) doc).getAuteur());
//            }
//        }
//    }
//
//
//}
