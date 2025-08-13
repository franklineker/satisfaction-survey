import { base64url } from 'jose';

/**
 * @description Generates a cryptographically secure random string for the code_verifier.
 * The output is URL-safe and conforms to the PKCE specification (RFC 7636).
 * @returns {string} The URL-safe Base64 encoded code verifier.
 */
export function generateCodeVerifier() {
    // Create a 32-byte (256-bit) array, which is the recommended size for PKCE.
    const randomBytes = new Uint8Array(32);
    // Populate the array with cryptographically secure random values.
    window.crypto.getRandomValues(randomBytes);
    // Encode the bytes into a URL-safe Base64 string.
    return base64url.encode(randomBytes);
}

/**
 * @description Generates the code_challenge from the code_verifier using SHA-256.
 * @param {string} codeVerifier The code verifier string.
 * @returns {Promise<string>} The URL-safe Base64 encoded code challenge.
 */
export async function generateCodeChallenge(codeVerifier) {
    console.log("code verifier inside the generateCodeChallenge function >>>", codeVerifier);

    const encoder = new TextEncoder();
    const data = encoder.encode(codeVerifier);

    const digest = await window.crypto.subtle.digest('SHA-256', data);
    const codeChallenge = base64url.encode(new Uint8Array(digest));

    console.log("code challenge >>>", codeChallenge);
    return codeChallenge;
}