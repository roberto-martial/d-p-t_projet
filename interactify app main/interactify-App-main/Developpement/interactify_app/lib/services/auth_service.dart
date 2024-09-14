import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:interactify_app/Pages/home_page.dart';
import 'package:interactify_app/models/Users.dart';
import 'package:shared_preferences/shared_preferences.dart';

class AuthService {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  // Method for registering a new user
  Future<Users?> register(
      String username, String promotion, String email, String password) async {
    try {
      UserCredential userCredential = await _auth
          .createUserWithEmailAndPassword(email: email, password: password);
      User? user = userCredential.user;

      if (user != null) {
        Users utilisateur = Users(
          id: user.uid,
          username: username,
          promotion: promotion,
          email: email,
        );

        await _firestore
            .collection('users')
            .doc(user.uid)
            .set(utilisateur.toJson());
        return utilisateur;
      }
      return null;
    } catch (e) {
      throw Exception('Registration failed: $e');
    }
  }

  // Method for logging in an existing user
  Future<Users?> login(String email, String password) async {
    try {
      UserCredential userCredential = await _auth.signInWithEmailAndPassword(
          email: email, password: password);
      User? user = userCredential.user;
      String userId = user!.uid;
      SharedPreferences prefs = await await SharedPreferences.getInstance();
      prefs.setString('userId', userId);

      DocumentSnapshot doc =
          await _firestore.collection('users').doc(user.uid).get();
      return Users.fromJson(doc.data() as Map<String, dynamic>);
    } catch (e) {
      throw Exception('Login failed: $e');
    }
  }

  Future<Users?> getCurrentUser() async {
    User? user = _auth.currentUser;
    if (user != null) {
      DocumentSnapshot doc =
          await _firestore.collection('users').doc(user.uid).get();
      return Users.fromJson(doc.data() as Map<String, dynamic>);
    }
    return null;
  }

  // Future<Users?> resetPassword() async {
  //   User? user = _auth.;
  //   if (user != null) {
  //     DocumentSnapshot doc =
  //         await _firestore.collection('users').doc(user.uid).get();
  //     return Users.fromJson(doc.data() as Map<String, dynamic>);
  //   }
  //   return null;
  // }

  Future<void> sendPasswordResetLink(String email) async {
    try {
      await _auth.sendPasswordResetEmail(email: email);
    } catch (e) {
      print(e);
    }
  }

  signInWithGoogle() async {
  try {
    // Essayer de se connecter avec Google
    GoogleSignInAccount? googleUser = await GoogleSignIn().signIn();
    
    if (googleUser == null) {
      // Si l'utilisateur annule la connexion, googleUser sera null
      print('Google sign-in was canceled by the user');
      return;
    }

    // Authentification avec Google
    GoogleSignInAuthentication? googleAuth = await googleUser.authentication;

    // Création des credentials pour Firebase
    AuthCredential credential = GoogleAuthProvider.credential(
      accessToken: googleAuth.accessToken,
      idToken: googleAuth.idToken,
    );

    // Connexion à Firebase avec les credentials
    UserCredential userCredential =
        await _auth.signInWithCredential(credential);
    
    if (userCredential.user != null) {
      // Si la connexion réussit, enregistrer l'ID utilisateur dans les SharedPreferences
      SharedPreferences prefs = await SharedPreferences.getInstance();
      prefs.setString('userId', userCredential.user!.uid);
    } else {
      print('Failed to sign in with Google');
    }
  } catch (error) {
    print('An error occurred during Google sign-in: $error');
  }
}

}
