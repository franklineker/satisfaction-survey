import { cn } from '@/lib/utils';
import { ThumbsUp } from 'lucide-react';

export default function YesThumbsUpButton({ className, ...props }) {
    return (
        <button
            className={cn("flex text-sm text-white border border-2 rounded-md border-black bg-gray-800 p-1 w-[50px] h-[30px] hover:cursor-pointer", className)}
            {...props}
            type='button'
        >
            <span>Sim</span><ThumbsUp className='fill-yellow-500 text-black w-4.5 h-4.5' />
        </button>
    )
}