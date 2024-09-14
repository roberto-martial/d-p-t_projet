import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:interactify_app/models/commentaire.dart';
import 'package:interactify_app/models/like.dart';

class Publication {
  final String? id;
  final String utilisateurId;
  final Timestamp datePublication;
  final String description;
  final String? image;
  final List<Like>? likes;
  final List<Commentaire>? commentaires;

  Publication({
    required this.id,
    required this.utilisateurId,
    required this.datePublication,
    required this.description,
    this.image,
    this.likes,
    this.commentaires,
  });

  static Publication convertFromMap(Map<String, dynamic> map) {
    return Publication(
      id: map['id'] ?? "",
      utilisateurId: map['utilisateur']['utilisateur'] ?? "",
      datePublication: map["datePublication"],
      description: map['description'] ?? "",
      image: map['image'] ?? "",
      likes: map['likes'] != null
          ? List<Like>.from(
              map['likes'].map((like) => Like.convertFromMap(like)))
          : [],
      commentaires: map['commentaires'] != null
          ? List<Commentaire>.from(map['commentaires']
              .map((commentaire) => Commentaire.convertFromMap(commentaire)))
          : [],
    );
  }

  static Map<String, dynamic> convertToMap(Publication publication) {
    Map<String, dynamic> publicationAsMap;

    publicationAsMap = {
      'utilisateurId': publication.utilisateurId,
      'datePublication': publication.datePublication,
      'description': publication.description,
      'image': publication.image!,
      // 'likes': publication.likes
      //     ?.map((like) => Like)
      //     .toList(),
      // 'commentaires': publication.commentaires
      //     ?.map((commentaire) => Commentaire.convertToMap(commentaire))
      //     .toList(),
    };
    if (publication.id != null) {
      publicationAsMap["id"] = publication.id!;
    }
    return publicationAsMap;
  }

  @override
  String toString() {
    return 'Publication{id: $id, utilisateurId: $utilisateurId, datePublication: $datePublication, description: $description, image: $image, likes: $likes, commentaires: $commentaires}';
  }
}
