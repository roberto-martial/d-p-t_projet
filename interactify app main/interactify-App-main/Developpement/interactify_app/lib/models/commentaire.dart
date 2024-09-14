class Commentaire {
  final String? id;
  final String utilisateurId;
  final String publicationId;
  final String text;
  final String date;

  Commentaire(
      { this.id,
      required this.utilisateurId,
      required this.publicationId,
      required this.text,
      required this.date});

  static Commentaire convertFromMap(Map<String, dynamic> map) {
    return Commentaire(
      id: map['id'],
      utilisateurId: map['utilisateurId'],
      publicationId: map['publicationId'],
      text: map['text'],
      date: map['date'],
    );
  }

  static Map<String, dynamic> convertToMap(Commentaire commentaire) {
    Map<String, String> commentaireAsMap;

    commentaireAsMap = {
      'utilisateurId': commentaire.utilisateurId,
      'publicationId': commentaire.publicationId,
      'text': commentaire.text,
      'date': commentaire.date,
    };
    if (commentaire.id != null) {
      commentaireAsMap["id"] = commentaire.id!;
    }

    return commentaireAsMap;
  }

    @override
  String toString() {
    return 'Commentaire{id: $id, utilisateurId: $utilisateurId, publicationId: $publicationId, text: $text, date: $date}';
  }
}
