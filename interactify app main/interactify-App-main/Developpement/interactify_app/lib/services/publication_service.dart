import 'dart:io';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:interactify_app/models/commentaire.dart';
import 'package:interactify_app/models/like.dart';
import 'package:interactify_app/models/publication.dart';
import 'package:interactify_app/models/users.dart';
import 'package:interactify_app/repository/publication_repository.dart';

class PublicationService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final FirebaseStorage _storage = FirebaseStorage.instance;

  Future<String?> uploadImage(File file) async {
    try {
      String fileName = DateTime.now().millisecondsSinceEpoch.toString();
      UploadTask task = _storage.ref('images/$fileName').putFile(file);
      TaskSnapshot snapshot = await task;
      String downloadUrl = await snapshot.ref.getDownloadURL();
      return downloadUrl;
    } catch (e) {
      print('Error uploading image: $e');
      return null;
    }
  }

  Future<void> addPost(Publication post, File imageFile) async {
    try {

      String? imageUrl = await uploadImage(imageFile);
      print(imageUrl);

      DocumentReference postRef = await _firestore.collection('posts').add({
        'userId': post.utilisateurId,
        'datePublication': post.datePublication,
        'description': post.description,
        'image': imageUrl
      });

      await postRef.collection('likes').doc('init').set({});

      await postRef.collection('comments').doc('init').set({});

      post.likes?.forEach((like) {
        postRef.collection('likes').add(Like.convertToMap(like));
      });
      post.commentaires?.forEach((comment) {
        postRef.collection('comments').add(Commentaire.convertToMap(comment));
      });
    } catch (e) {
      print('Error adding post: $e');
      throw e;
    }
  }

  Future<void> addLike(String postId, Like like) async {
    try {
      await _firestore
          .collection('posts')
          .doc(postId)
          .collection('likes')
          .add(Like.convertToMap(like));
    } catch (e) {
      print('Error adding like: $e');
      throw e;
    }
  }

  Future<void> removeLike(String postId, String likeId) async {
    try {
      await _firestore
          .collection('posts')
          .doc(postId)
          .collection('likes')
          .doc(likeId)
          .delete();
    } catch (e) {
      print('Error removing like: $e');
      throw e;
    }
  }

  Future<void> addComment(String postId, Commentaire comment) async {
    try {
      await _firestore
          .collection('posts')
          .doc(postId)
          .collection('commentaires')
          .add(Commentaire.convertToMap(comment));
    } catch (e) {
      print('Error adding comment: $e');
      throw e;
    }
  }

  Future<List<Commentaire>> getComments(String postId) async {
    try {
      QuerySnapshot commentsSnapshot = await _firestore
          .collection('posts')
          .doc(postId)
          .collection('commentaires')
          .get();
      return commentsSnapshot.docs
          .map((doc) =>
              Commentaire.convertFromMap(doc.data() as Map<String, dynamic>))
          .toList();
    } catch (e) {
      print('Error getting comments: $e');
      throw e;
    }
  }

  Future<List<Publication>> getPosts() async {
    try {
      QuerySnapshot postSnapshot = await _firestore.collection('posts').get();

      List<Publication> posts = [];

      for (var doc in postSnapshot.docs) {
        // Handle missing userId
        var userId = doc['userId'] ?? 'Unknown';

        // Fetch user data and handle potential nulls
        var userDoc = await _firestore.collection('users').doc(userId).get();
        var userData = userDoc.data() ?? {};

        // Fetch likes and comments
        var likesSnapshot = await doc.reference.collection('likes').get();
        var commentsSnapshot = await doc.reference.collection('comments').get();

        List<Like> likes = likesSnapshot.docs.map((d) {
          var data = d.data() as Map<String, dynamic>;
          return Like.convertFromMap(data);
        }).toList();

        List<Commentaire> comments = commentsSnapshot.docs.map((d) {
          var data = d.data() as Map<String, dynamic>;
          return Commentaire.convertFromMap(data);
        }).toList();

        // Create Users object with fallback values for null fields
        Users utilisateur = Users(
          id: userId,
          photo: userData['photo'] ?? 'Unknown',
          promotion: userData['promotion'] ?? 'Unknown',
          username: userData['username'] ?? 'Unknown',
          email: userData['email'] ?? 'Unknown',
        );

        // Create Publication object with fallback values for null fields
        Publication post = Publication(
          id: doc.id,
          utilisateurId: utilisateur.id,
          datePublication: doc['datePublication'] ?? 'Unknown',
          description: doc['description'] ?? 'Unknown',
          //image: (doc['image'] != null && doc['image'].isNotEmpty) ? doc['image'] : 'Unknown',
          likes: likes,
          commentaires: comments,
        );

        posts.add(post);
      }

      return posts;
    } catch (e) {
      print('Error getting posts: $e');
      throw e;
    }
  }

  Future<Users?> getCurrentUser() async {
    try {
      User? firebaseUser = FirebaseAuth.instance.currentUser;
      if (firebaseUser != null) {
        DocumentSnapshot userSnapshot =
            await _firestore.collection('users').doc(firebaseUser.uid).get();
        if (userSnapshot.exists) {
          var userData = userSnapshot.data() as Map<String, dynamic>;
          return Users.fromJson(userData);
        }
      }
    } catch (e) {
      print('Error getting current user: $e');
    }
    return null;
  }

  PublicationRepository publicationRepository = PublicationRepository();

  Future<List<Publication>> getAllPub() async {
    return await publicationRepository.getAllPublications();
  }
}
