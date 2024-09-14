import 'package:flutter/material.dart';

class ButtonBlackBorder extends StatelessWidget {
  const ButtonBlackBorder(
      {super.key, required this.texte, required this.onPressed});

  final String texte;
  final void Function() onPressed;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      child: TextButton(
        onPressed: onPressed,
        style: TextButton.styleFrom(
          backgroundColor: Colors.white, // Button background color
          padding: EdgeInsets.symmetric(vertical: 16),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8),
            side: BorderSide(color: Color(0xFF051C24), width: 1),
          ),
        ),
        child: Text(
          texte,
          style: TextStyle(
            color: Color(0xFF051C24), // Button text color
            fontWeight: FontWeight.w600,
            fontSize: 20,
          ),
        ),
      ),
    );
  }
}
