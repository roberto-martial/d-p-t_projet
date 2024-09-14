import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:interactify_app/models/Users.dart';

class UserService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final FirebaseStorage _storage = FirebaseStorage.instance;

  Future<List<Users>> getAllUsers() async {
    try {
      QuerySnapshot usersSnapshot = await _firestore.collection('users').get();
      
      return usersSnapshot.docs
          .map((doc) => Users.fromJson(doc.data() as Map<String, dynamic>))
          .toList();
    } catch (e) {
      print('Error getting comments: $e');
      throw e;
    }
  }
}
