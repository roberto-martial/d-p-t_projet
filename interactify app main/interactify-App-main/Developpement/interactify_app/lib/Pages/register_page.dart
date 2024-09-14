import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:interactify_app/Pages/login_page.dart';
import 'package:interactify_app/Pages/login_register_page.dart';
import 'package:interactify_app/services/auth_service.dart';
import 'package:interactify_app/widgets/button_black.dart';
import 'package:interactify_app/widgets/input_password.dart';
import 'package:interactify_app/widgets/input_text.dart';

class RegisterPage extends StatefulWidget {
  static const routeName = "/register";

  const RegisterPage({super.key});

  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _emailController = TextEditingController();
  final _promotionController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  final AuthService _authService = AuthService();
  bool _isLoading = false;

  void _register() async {
    if (_formKey.currentState!.validate()) {
      if (_passwordController.text != _confirmPasswordController.text) {
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Passwords do not match')),
        );
        return;
      }
      setState(() {
        _isLoading = true;
      });

      try {
        var user = await _authService.register(
          _usernameController.text,
          _promotionController.text,
          _emailController.text,
          _passwordController.text,
        );
        if (user != null) {
          Navigator.pushNamed(context, LoginPage.routeName);
        }
      } catch (e) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Registration failed: $e')),
        );
      } finally {
        setState(() {
          _isLoading = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 18),
      child: Scaffold(
        appBar: AppBar(
          leading: Container(
            padding: const EdgeInsets.symmetric(vertical: 10),
            decoration: BoxDecoration(
              border: Border.all(
                color: Colors.grey,
                width: 0.3,
              ),
              borderRadius: BorderRadius.circular(8),
            ),
            child: IconButton(
              hoverColor: Colors.transparent,
              icon: const Icon(
                Icons.arrow_back_ios_new_rounded,
                color: Color(0xFF051C24),
              ),
              onPressed: () {
                Navigator.pushNamed(context, LoginRegisterPage.routeName);
              },
            ),
          ),
          elevation: 0,
          backgroundColor: Colors.transparent,
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(vertical: 10),
          child: _isLoading
              ? const Center(child: CircularProgressIndicator())
              : Form(
                  key: _formKey,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      const Text(
                        'Hello! Register to get started',
                        style: TextStyle(
                          fontSize: 30,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      InputText(
                        controller: _usernameController,
                        hintText: "Username",
                        validator: (value) => value!.isEmpty ? 'Please enter a username' : null,
                      ),
                      InputText(
                        controller: _emailController,
                        hintText: "Email",
                        validator: (value) => value!.isEmpty ? 'Please enter an email' : null,
                      ),
                      InputText(
                        controller: _promotionController,
                        hintText: "Promotion",
                        validator: (value) => value!.isEmpty ? 'Please enter a promotion' : null,
                      ),
                      InputPassword(
                        controller: _passwordController,
                        hintText: "Password",
                        validator: (value) => value!.isEmpty ? 'Please enter a password' : null,
                      ),
                      InputPassword(
                        controller: _confirmPasswordController,
                        hintText: "Confirm Password",
                        validator: (value) => value!.length < 6 ? 'Password too short' : null,
                      ),
                      Padding(
                        padding: const EdgeInsets.symmetric(vertical: 10),
                        child: ButtonBlack(
                          texte: "Register",
                          onPressed: _register,
                        ),
                      ),
                      
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          RichText(
                            text: TextSpan(
                              text: 'Already have an account? ',
                              style: const TextStyle(
                                color: Colors.black,
                                fontSize: 16,
                              ),
                              children: [
                                TextSpan(
                                  text: 'Login Now',
                                  style: const TextStyle(
                                    color: Colors.blue,
                                    fontSize: 16,
                                    fontWeight: FontWeight.w600,
                                  ),
                                  recognizer: TapGestureRecognizer()
                                    ..onTap = () {
                                      Navigator.pushNamed(context, LoginPage.routeName);
                                    },
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
        ),
      ),
    );
  }
}
