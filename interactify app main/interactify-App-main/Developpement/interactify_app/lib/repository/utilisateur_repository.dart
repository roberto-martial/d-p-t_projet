import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:interactify_app/models/utilisateur.dart';

class UtilisateurRepository {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final CollectionReference _usersCollection =
      FirebaseFirestore.instance.collection('users');

  Future<List<Utilisateur>> getAllUtilisateur() async {
    QuerySnapshot querySnapshot = await _usersCollection.get();
    return querySnapshot.docs.map((doc) {
      return Utilisateur.convertFromMap(doc.data() as Map<String, dynamic>);
    }).toList();
  }

  Future<Utilisateur?> getUserById(String id) async {
    DocumentSnapshot docSnapshot = await _usersCollection.doc(id).get();
    if (docSnapshot.exists) {
      return Utilisateur.convertFromMap(docSnapshot.data() as Map<String, dynamic>);
    }
    return null;
  }

  Future<void> updateUser(Utilisateur utilisateur) async {
        Map<String, String> utilisateurAsMap;

        utilisateurAsMap = {
      "username": utilisateur.username,
      "promotion": utilisateur.promotion,
      "email": utilisateur.email,
      "photoProfil": utilisateur.photoProfil ?? ""
    };
    if (utilisateur.id != null) {
      utilisateurAsMap["id"] = utilisateur.id!;
    }
    await _usersCollection.doc(utilisateur.id).update(utilisateurAsMap);
  }
}
