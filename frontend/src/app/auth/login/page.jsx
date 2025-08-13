// src/app/auth/login/page.js
'use client'; // <-- ESSA DIRETIVA É CRUCIAL

import Link from 'next/link';
import { useEffect, useState } from 'react';
import { getAuthorizationUrl } from '@/lib/auth';
import { generateCodeChallenge, generateCodeVerifier } from '@/lib/pkce';

export default function LoginPage() {
    const [authorizationUrl, setAuthorizationUrl] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // A lógica PKCE roda aqui, garantindo acesso à API window.crypto
        const setupPKCE = async () => {
            try {
                const codeVerifier = generateCodeVerifier();

                // Armazena o verifier na sessão
                sessionStorage.setItem('code_verifier', codeVerifier);

                const codeChallenge = await generateCodeChallenge(codeVerifier);
                const url = getAuthorizationUrl(codeChallenge);
                setAuthorizationUrl(url);
            } catch (error) {
                console.error('Failed to generate PKCE parameters:', error);
            } finally {
                setLoading(false);
            }
        };

        setupPKCE();
    }, []);

    if (loading) {
        return <div>Gerando URL de login...</div>;
    }

    return (
        <div>
            <h1>Entrar no Dr. Navalha</h1>
            <p>Você precisa estar logado para acessar.</p>
            <Link href={authorizationUrl}>
                <button>Login com o Authorization Server</button>
            </Link>
        </div>
    );
}