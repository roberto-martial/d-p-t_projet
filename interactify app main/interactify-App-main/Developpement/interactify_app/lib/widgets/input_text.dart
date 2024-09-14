import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class InputText extends StatefulWidget {
  final String hintText;
  final TextEditingController? controller;
  final String? Function(String?)? validator;

  const InputText({
    super.key,
    required this.hintText,
    this.controller,
    this.validator,
  });

  @override
  State<InputText> createState() => _InputTextState();
}

class _InputTextState extends State<InputText> {
  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: widget.controller,
      validator: widget.validator,
      cursorColor: Color(0xFF2EA3F8),
      decoration: InputDecoration(
        filled: true,
        fillColor: Color(0xFFF7F8F9),
        hintText: widget.hintText,
        hintStyle: GoogleFonts.urbanist(), // Correct way to apply Google Fonts
        border: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(8.0)),
          borderSide: BorderSide(
            color: Colors.grey,
            width: 0.3,
          ),
        ),
      ),
    );
  }
}
