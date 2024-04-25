/*import bcrypt from 'bcrypt';
import express, { Request, Response, Router } from 'express';
import jwt from 'jsonwebtoken';
import { Credentials } from '../models/credentials'; // Ensure you import the User model correctly

// Type for expected user payload in the request body
interface UserPayload {
    name?: string;
    email?: string;
    password?: string;
}

// Creating a new Router object
const router: Router = express.Router();

// POST endpoint for registering a new user
router.post('/register', async (req: Request<{}, {}, UserPayload>, res: Response) => {
    console.log("Received register request with data:", req.body);

    // Check for missing fields
    if (!req.body.name || !req.body.email || !req.body.password) {
        return res.status(400).send('Missing fields.');
    }

    // Check for valid email format
    const emailRegex: RegExp = /\S+@\S+\.\S+/;
    if (!emailRegex.test(req.body.email)) {
        return res.status(400).send('Invalid email format.');
    }

    // Check for password strength (e.g., minimum length of 10 here)
    if (req.body.password.length < 10) {
        return res.status(400).send('Password too short.');
    }

    // Check if user already exists
    const existingUser = await Credentials.findOne({ email: req.body.email });
    if (existingUser) {
        return res.status(400).send('Email already registered.');
    }

    // The actual user registration logic
    try {
        const hashedPassword = await bcrypt.hash(req.body.password, 10);
        const user = new Credentials({
            name: req.body.name,
            email: req.body.email,
            password: hashedPassword
        });
        await user.save();

        const userPayload = {
            id: user._id,
            name: user.name,
            email: user.email
        };
        const SECRET_KEY = 'your_secret_key'; // Make sure to define this securely
        const token = jwt.sign(userPayload, SECRET_KEY, { expiresIn: '1h' });

        res.status(201).json({ success: true, message: 'User registered', token });
    } catch (err: any) {
        res.status(400).json({ success: false, message: err.message });
    }
});

export default router;*/