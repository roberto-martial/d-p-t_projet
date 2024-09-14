class Like {
  final String? id;
  final String utilisateurId;
  final String publicationId;

  Like(
      { this.id,
      required this.utilisateurId,
      required this.publicationId});

  static Like convertFromMap(Map<String, dynamic> map) {
    return Like(
      id: map['id'],
      utilisateurId: map['utilisateurId'],
      publicationId: map['publicationId'],
    );
  }

  static Map<String, dynamic> convertToMap(Like like) {
    Map<String, String> likeAsMap;

    likeAsMap = {
      'utilisateurId': like.utilisateurId,
      'publicationId': like.publicationId,
    };
    if (like.id != null) {
      likeAsMap["id"] = like.id!;
    }

    return likeAsMap;
  }

    @override
  String toString() {
    return 'Like{id: $id, utilisateurId: $utilisateurId, publicationId: $publicationId}';
  }
}
