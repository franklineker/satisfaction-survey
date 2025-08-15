import { base64url } from "jose";

export function getAuthorizationUrl(codeChallenge: string): string {
    // Retrieve environment variables
    const clientId = process.env.NEXT_PUBLIC_OAUTH2_CLIENT_ID;
    const redirectUri = process.env.NEXT_PUBLIC_OAUTH2_REDIRECT_URI;
    const authServerUrl = process.env.NEXT_PUBLIC_AUTH_SERVER_URL;

    if (!clientId || !redirectUri || !authServerUrl) {
        throw new Error("Required environment variables are not defined.");
    }

    const params = new URLSearchParams({
        response_type: 'code',
        client_id: clientId,
        redirect_uri: redirectUri,
        scopes: 'openid profile email',
        code_challenge: codeChallenge,
        code_challenge_method: 'S256'
    });

    return `${authServerUrl}/oauth2/authorize?${params.toString()}`;
}

export function generateCodeVerifier() {
    const randomBytes = new Uint8Array(32);
    window.crypto.getRandomValues(randomBytes);
    return base64url.encode(randomBytes);
}

export async function generateCodeChallenge(codeVerifier: string) {
    const encoder = new TextEncoder();
    const data = encoder.encode(codeVerifier);

    const digest = await window.crypto.subtle.digest('SHA-256', data);
    const codeChallenge = base64url.encode(new Uint8Array(digest));

    return codeChallenge;
}