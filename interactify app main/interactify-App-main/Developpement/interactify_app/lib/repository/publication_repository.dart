import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:interactify_app/models/commentaire.dart';
import 'package:interactify_app/models/like.dart';
import 'package:interactify_app/models/publication.dart';

class PublicationRepository {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  final CollectionReference _publicationCollection =
      FirebaseFirestore.instance.collection('posts');

  Future<List<Publication>> getAllPublications() async {
    QuerySnapshot querySnapshot = await _publicationCollection.get();
    return querySnapshot.docs.map((doc) {
      return Publication.convertFromMap(doc.data() as Map<String, dynamic>);
    }).toList();
  }

  Future<List<Publication>> getPublicationsByUserId(String userId) async {
    QuerySnapshot querySnapshot = await _publicationCollection
        .where('utilisateurId', isEqualTo: userId)
        .get();
    return querySnapshot.docs.map((doc) {
      return Publication.convertFromMap(doc.data() as Map<String, dynamic>);
    }).toList();
  }

  Future<List<Commentaire>> getCommentairesByPublicationId(
      String publicationId) async {
    QuerySnapshot docSnapshot = await _publicationCollection
        .doc(publicationId)
        .collection("commentaires")
        .get();
    if (docSnapshot.docs.isNotEmpty) {
      List<Commentaire> commentaires = (docSnapshot.docs)
          .map((comment) =>
              Commentaire.convertFromMap(comment as Map<String, dynamic>))
          .toList();
      return commentaires;
    }
    return [];
  }

  Future<List<Like>> getLikesByPublicationId(String publicationId) async {
    QuerySnapshot docSnapshot = await _publicationCollection
        .doc(publicationId)
        .collection("likes")
        .get();
    if (docSnapshot.docs.isNotEmpty) {
      List<Like> likes = (docSnapshot.docs)
          .map((like) => Like.convertFromMap(like as Map<String, dynamic>))
          .toList();
      return likes;
    }
    return [];
  }
}
