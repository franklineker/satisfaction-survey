'use client';

import { useSearchParams } from 'next/navigation';
import { useEffect } from 'react';

export default function CallbackPage() {
    const searchParams = useSearchParams();
    const code = searchParams.get('code');

    useEffect(() => {
        if (code) {
            const codeVerifier = sessionStorage.getItem('code_verifier');

            if (!codeVerifier) {
                console.error("Code verifier not found in session storage.");
                window.location.href = '/auth/login?error=true';
                return;
            }

            sessionStorage.removeItem('code_verifier');

            fetch('/auth/api/token', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ code, codeVerifier }),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Falha na troca de tokens');
                    }
                    return response.json();
                })
                .then(tokens => {
                    console.log('Tokens recebidos com sucesso:', tokens);
                    // Lógica para armazenar tokens e redirecionar
                    window.location.href = '/home';
                })
                .catch(error => {
                    console.error(error);
                    window.location.href = '/auth/login?error=true';
                });
        }
    }, [code]);

    return (
        <div>
            Carregando...
        </div>
    );
}