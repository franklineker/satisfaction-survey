export function getAuthorizationUrl(codeChallenge) {
    const params = new URLSearchParams({
        response_type: 'code',
        client_id: process.env.NEXT_PUBLIC_OAUTH2_CLIENT_ID,
        redirect_uri: process.env.NEXT_PUBLIC_OAUTH2_REDIRECT_URI,
        scope: 'openid profile email',
        code_challenge: codeChallenge,
        code_challenge_method: 'S256',
    });

    console.log("server url >>>: ", process.env.NEXT_PUBLIC_AUTH_SERVER_URL)
    return `${process.env.NEXT_PUBLIC_AUTH_SERVER_URL}/oauth2/authorize?${params.toString()}`;
}