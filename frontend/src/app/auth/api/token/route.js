import { NextResponse } from 'next/server';
import { encode } from 'querystring';

export async function POST(request) {
    const { code, codeVerifier } = await request.json();

    if (!code || !codeVerifier) {
        return NextResponse.json({ error: 'Authorization code or verifier is missing' }, { status: 400 });
    }

    const tokenUrl = `${process.env.NEXT_PUBLIC_AUTH_SERVER_URL}/oauth2/token`;

    try {
        const response = await fetch(tokenUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': `Basic ${btoa(`${process.env.NEXT_PUBLIC_OAUTH2_CLIENT_ID}:${process.env.OAUTH2_CLIENT_SECRET}`)}`,
            },
            body: encode({
                grant_type: 'authorization_code',
                code: code,
                redirect_uri: process.env.NEXT_PUBLIC_OAUTH2_REDIRECT_URI,
                code_verifier: codeVerifier
            }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            console.error('Error fetching token:', errorData);
            return NextResponse.json({ error: 'Failed to exchange authorization code' }, { status: response.status });
        }

        const tokens = await response.json();
        return NextResponse.json(tokens);

    } catch (error) {
        console.error('API Route error:', error);
        return NextResponse.json({ error: 'Internal server error' }, { status: 500 });
    }
}

